package com.api.serviceImpl;

import com.api.model.Banner;
import com.api.model.Good;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.service.BannerService;
import com.api.service.GoodService;
import com.api.service.IndexService;
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
@Transactional
public class IndexServiceImpl implements IndexService{

    @Autowired
    private BannerService bannerService;

    @Autowired
    private GoodService goodService;

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

        if(bannerList != null && bannerList.size() > 0) {
            for (Banner b : bannerList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("imgUrls", b.getImgurl());
                map.put("name", b.getName());
                map.put("linkurl", b.getLinkurl());
                banner.add(map);
            }
        }

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
        result.put("banner",banner);
        result.put("newlist",good);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

}
