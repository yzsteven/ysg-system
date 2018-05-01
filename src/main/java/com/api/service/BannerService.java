package com.api.service;

import com.api.model.Banner;
import com.api.model.Good;
import com.api.model.Response;

import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
public interface BannerService {

    public List<Banner> queryBannerList(String cid,int type);

    public Response doAddBanner(Banner banner);

    public Banner queryBannerById(long id);

    public Response doEditBanner(Banner banner);

    public Response deleBanner(long id);

    public Response queryBannerListByCID(Banner banner);

    public int countBannerListAll(Banner banner);

}
