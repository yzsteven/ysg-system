package com.api.service;

import com.api.model.Response;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
public interface IndexService {

    public Response index(Long cid);

    public Response queryAdvertisement(Long cid,int type);

    public Response queryGoodDetail(Long id);

    public Response queryCategory(Long cid);
}
