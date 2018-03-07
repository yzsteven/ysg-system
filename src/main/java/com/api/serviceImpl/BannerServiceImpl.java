package com.api.serviceImpl;

import com.api.dao.BannerMapper;
import com.api.model.Banner;
import com.api.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 获取banner图
     * @param cid 店铺id
     * @param type
     * @return
     */
    public List<Banner> queryBannerList(Long cid, int type) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("cid",cid);
        param.put("type",type);
        return bannerMapper.selectBannerList(param);
    }

}
