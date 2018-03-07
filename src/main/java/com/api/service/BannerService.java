package com.api.service;

import com.api.model.Banner;
import com.api.model.Good;

import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
public interface BannerService {

    public List<Banner> queryBannerList(Long cid,int type);

}
