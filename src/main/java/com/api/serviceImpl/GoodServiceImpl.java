package com.api.serviceImpl;

import com.api.dao.GoodMapper;
import com.api.dao.PictureMapper;
import com.api.dao.SpecMapper;
import com.api.model.*;
import com.api.service.GoodService;
import com.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
@Service
@Transactional
public class GoodServiceImpl implements GoodService{


    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 获取新品/推荐/精选 商品列表
     * @param cid
     * @param type 1 isnew 2 isrecommend 3isselected
     * @return
     */
    public List<HashMap<String,Object>> queryGoodsList(String cid, int type) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("cid",cid);
        switch (type){
            case 1 : param.put("isnew",1);break;
            case 2 : param.put("isrecommend",1);break;
            case 3 : param.put("isselected",1);break;
        }
        return goodMapper.selectGoodsList(param);
    }

    /**
     *获取商品详情
     * @param id
     * @return
     */
    public HashMap<String, Object> queryGoodDetail(Long id) {
        return goodMapper.selectGoodInfo(id);
    }

    /**
     * 获取商品列表
     * @param id
     * @return
     */
    public List<HashMap<String, Object>> queryGoodListByCategory(Long id) {
        return goodMapper.selectGoodListByCategory(id);
    }

    /**
     * 后台获取商品列表
     * @param good
     * @return
     */
    public Response queryGoodListByCID(Good good) {
        HashMap<String,Object> param = new HashMap<String, Object>();

        param.put("cid",good.getCid());

        if(good.getCategoryid() != null){
            param.put("categoryId",good.getCategoryid());
        }

        if(good.getPageHelper().getPageSize() != 0 && good.getPageHelper().getPageNumber() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (good.getPageHelper().getPageNumber() - 1) * good.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",good.getPageHelper().getPageSize());
        }

        if(!StringUtil.isBlank(good.getPageHelper().getSearchParam())){
            param.put("search",good.getPageHelper().getSearchParam());
        }

        List<HashMap<String,Object>> goodList = goodMapper.selectGoodListByCID(param);

        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),goodList);
    }

    public int countGoodListAll(Good good) {
        HashMap<String,Object> param = new HashMap<String, Object>();

        param.put("cid",good.getCid());

        if(good.getCategoryid() != null){
            param.put("categoryId",good.getCategoryid());
        }

        if(good.getPageHelper().getPageSize() != 0 && good.getPageHelper().getPageNumber() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (good.getPageHelper().getPageNumber() - 1) * good.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",good.getPageHelper().getPageSize());
        }

        if(!StringUtil.isBlank(good.getPageHelper().getSearchParam())){
            param.put("search",good.getPageHelper().getSearchParam());
        }

        int count = goodMapper.selectCountGoodList(param);

        return count;
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    public Response deleGoodById(Long id) {
        String result = "success";
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        Good good = new Good();
        good.setId(id);
        good.setIsdel(1);
        good.setUpdateTime(new Date());
        good.setUpdateBy(user.getUsername());
        int count = goodMapper.updateByPrimaryKeySelective(good);
        if(count <= 0){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     * 添加商品
     * @param good
     * @return
     */
    public Response doAddGoods(Good good) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        String result = "success";
        String pictureIds = "";
        good.setIsdel(0);
        good.setCreateTime(new Date());
        good.setCid(user.getCompany());
        good.setCreateBy(user.getUsername());
        if(!StringUtil.isBlank(good.getImages())){
            String[] imgs = good.getImages().split(",");
            pictureIds = addPicture(imgs,user,0);
        }
        good.setImages(pictureIds);
        int count = goodMapper.insertSelective(good);//插入商品
        if(count <= 0){
            result = "fail";
            throw new IUFailException();
        }

        List<Spec> specList = good.getSpec();
        String specResult = addSpec(specList,user,good);
        if(specResult != "success"){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }


    /**
     * 编辑商品
     * @param good
     * @return
     */
    public Response editGoodsInfo(Good good) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        String result = "success";
        String pictureIds = "";
        good.setIsdel(0);
        good.setUpdateTime(new Date());
        good.setCid(user.getCompany());
        good.setUpdateBy(user.getUsername());
        if(!StringUtil.isBlank(good.getImages())){
            String[] imgs = good.getImages().split(",");
            pictureIds = addPicture(imgs,user,1);
        }
        good.setImages(pictureIds);
        int count = goodMapper.updateByPrimaryKeySelective(good);//插入商品
        if(count <= 0){
            result = "fail";
            throw new IUFailException();
        }

        List<Spec> specList = good.getSpec();
        if(specList != null && specList.size() > 0){
            HashMap<String,Object> param = new HashMap<String, Object>();
            param.put("gid",good.getId());
            param.put("updateBy",user.getUsername());
            param.put("updateTime",new Date());
            param.put("isdel",1);
            int count3 = specMapper.updateByGid(param);
            if(count3 <= 0){
                result = "fail";
                throw new IUFailException();
            }
            String specResult = addSpec(specList,user,good);
            if(specResult != "success"){
                result = "fail";
            }
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    public String addSpec(List<Spec> specList,User user,Good good){
        String result = "success";
        for(Spec spec : specList){
            spec.setCreateBy(user.getUsername());
            spec.setIsdel(0);
            spec.setCreateTime(new Date());
            spec.setOrder(1);
            spec.setGid(good.getId());
            int count2 = specMapper.insertSelective(spec);//插入分类
            if(count2 < 0){
                result = "fail";
                throw new IUFailException();
            }
        }
        return result;
    }

    /**
     * 添加图片入库
     * @return
     */
    public String addPicture(String[] imgs,User user,int operate){
        String pictureIds = "";
            if(imgs.length > 0){
                for(int i = 0;i<imgs.length;i++){
                    Picture picture = new Picture();
                    picture.setIsdel("0");
                    if(operate == 0) {//新增
                        picture.setCreateBy(user.getUsername());
                        picture.setCreateTime(new Date());
                    }else{
                        picture.setUpdateBy(user.getUsername());
                        picture.setUpdateTime(new Date());
                    }
                    picture.setUrl(imgs[i]);
                    long count = pictureMapper.insertSelective(picture);
                    if(count > 0){
                        pictureIds += picture.getId() + ",";
                    }
                }
            }
        return pictureIds;
    }

    /**
     *  获取商品详情
     * @param id
     * @return
     */
    public HashMap<String,Object> queryGoodDetailForAdmin(Long id) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        HashMap<String,Object> goodDetail = this.queryGoodDetail(id);
        List<HashMap<String,Object>> priceList = specMapper.selectPriceByGid(id);
        goodDetail.put("price",priceList);
        result.put("good",goodDetail);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("".split(","));
    }
}
