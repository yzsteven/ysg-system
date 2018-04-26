package com.api.serviceImpl;

import com.api.dao.CategoryMapper;
import com.api.model.*;
import com.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
@Service
public class IndexServiceImpl implements IndexService{

    @Autowired
    private BannerService bannerService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private SpecService specService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private  PictureService pictureService;


    /**
     * 获取首页数据接口
     * @param cid
     * @return
     */
    public Response index(String cid) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<Banner> bannerList = bannerService.queryBannerList(cid,1);
        List<HashMap<String,Object>> goodList = goodService.queryGoodsList(cid,1);
        List<HashMap<String,Object>> banner = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String,Object>> good = new ArrayList<HashMap<String, Object>>();
        //处理数据
        this.addBannerData(bannerList,banner);
        this.addGoodData(goodList,good);
        result.put("banner",banner);
        result.put("newlist",good);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }


    /**
     * 获取新品/推荐/精选 列表 基本同首页接口暂时不做合并
     * @param cid
     * @param type
     * @return 1 isnew 2 isrecommend 3isselected
     */
    public Response queryAdvertisement(String cid, int type) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        int bannerType = 2;
        if(type == 1){//列表类型 isnew
            bannerType = 2;//广告图类型
        }else if(type == 2){//列表类型 isrecommend
            bannerType = 3;
        }else{//列表类型 3isselected
            bannerType = 4;
        }
        List<Banner> bannerList = bannerService.queryBannerList(cid,bannerType);
        List<HashMap<String,Object>> goodList = goodService.queryGoodsList(cid,type);
        List<HashMap<String,Object>> banner = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String,Object>> good = new ArrayList<HashMap<String, Object>>();
        //处理数据
        this.addBannerData(bannerList,banner);
        this.addGoodData(goodList,good);

        result.put("banner",banner);
        result.put("list",good);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     *  获取商品详情
     * @param id
     * @return
     */
    public Response queryGoodDetail(Long id) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        HashMap<String,Object> goodDetail = goodService.queryGoodDetail(id);
        List<String> images = new ArrayList<String>();
        String pictures = (String) goodDetail.get("images");
        if(pictures != null && pictures != ""){
            for(String pid : pictures.split(",")){
                Picture picture = pictureService.queryPictureInfo(Long.valueOf(pid));
                images.add(picture.getUrl());
            }
        }
        goodDetail.put("images",images);
        List<HashMap<String,Object>> priceList = specService.querySpecList(id);
        goodDetail.put("price",priceList);
        result.put("good",goodDetail);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    public Response queryCategory(String cid) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<HashMap<String,Object>> categoryList = categoryService.queryCategoryList(cid);
        List<HashMap<String,Object>> category = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String,Object>> detail = new ArrayList<HashMap<String, Object>>();
        for(HashMap<String,Object> item : categoryList){
            HashMap<String,Object> map = new HashMap<String,Object>();
            HashMap<String,Object> map2 = new HashMap<String,Object>();
            map.put("id",item.get("description"));
            map.put("name",item.get("name"));
            map2.put("id",item.get("description"));
            map2.put("banner",item.get("banner"));
            map2.put("cate",item.get("description"));
            List<HashMap<String,Object>>  goodList = goodService.queryGoodListByCategory((Long) item.get("id"));
            map2.put("detail",goodList);
            category.add(map);
            detail.add(map2);
        }
        result.put("category",category);
        result.put("detail",detail);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    private void addBannerData(List<Banner> bannerList,List<HashMap<String,Object>> banner){
        if(bannerList != null && bannerList.size() > 0) {
            for (Banner b : bannerList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("imgUrls", b.getImgurl());
                map.put("name", b.getName());
                map.put("linkurl", b.getLinkurl());
                banner.add(map);
            }
        }
    }

    private void addGoodData(List<HashMap<String,Object>> goodList,List<HashMap<String,Object>> good){
        if(goodList != null && goodList.size() > 0) {
            for (HashMap<String, Object> g : goodList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("imgUrls", g.get("coverImg"));
                map.put("name", g.get("name"));
                map.put("gid", g.get("id"));
                map.put("spec", g.get("spec"));
                map.put("price", g.get("price"));
                good.add(map);
            }
        }
    }

    public static void main(String[] args) {
        for(String pid : "".split(",")){
            System.out.println(pid);
        }
    }

}
