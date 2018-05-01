package com.api.serviceImpl;

import com.api.dao.BannerMapper;
import com.api.model.Banner;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.service.BannerService;
import com.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 获取banner图
     * @param cid 店铺id
     * @param type
     * @return
     */
    public List<Banner> queryBannerList(String cid, int type) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("cid",cid);
        param.put("type",type);
        return bannerMapper.selectBannerList(param);
    }

    /**
     * 添加轮播图
     * @param banner
     * @return
     */
    public Response doAddBanner(Banner banner) {
        String result = "success";
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        banner.setIsdel(0);
        banner.setCid(user.getCompany());
        banner.setType("1");
        banner.setCreateTime(new Date());
        banner.setCreateBy(user.getUsername());
        int count = bannerMapper.insertSelective(banner);
        if(count <= 0){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     * 获取轮播图信息
     * @param id
     * @return
     */
    public Banner queryBannerById(long id) {
        return bannerMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改轮播图信息
     * @param banner
     * @return
     */
    public Response doEditBanner(Banner banner) {
        String result = "success";
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        banner.setUpdateTime(new Date());
        banner.setUpdateBy(user.getUsername());
        int count = bannerMapper.updateByPrimaryKeySelective(banner);
        if(count <= 0){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }


    /**
     * 删除轮播图
     * @param id
     * @return
     */
    public Response deleBanner(long id) {
        String result = "success";
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        Banner banner = new Banner();
        banner.setId(id);
        banner.setIsdel(1);
        banner.setUpdateTime(new Date());
        banner.setUpdateBy(user.getUsername());
        int count = bannerMapper.updateByPrimaryKeySelective(banner);
        if(count <= 0){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     * 获取轮播图列表
     * @param banner
     * @return
     */
    public Response queryBannerListByCID(Banner banner) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        if(banner.getPageHelper().getPageSize() != 0 && banner.getPageHelper().getPageNumber() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (banner.getPageHelper().getPageNumber() - 1) * banner.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",banner.getPageHelper().getPageSize());
        }
        if(!StringUtil.isBlank(banner.getPageHelper().getSearchParam())){
            param.put("search",banner.getPageHelper().getSearchParam());
        }
        param.put("cid",banner.getCid());
        List<HashMap<String,Object>> bannerList = bannerMapper.selectBanner(param);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),bannerList);
    }

    /**
     * 获取轮播图数量
     * @param banner
     * @return
     */
    public int countBannerListAll(Banner banner) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("cid",banner.getCid());
        if(!StringUtil.isBlank(banner.getPageHelper().getSearchParam())){
            param.put("search",banner.getPageHelper().getSearchParam());
        }
        return bannerMapper.selectCountBannerAll(param);
    }

}
