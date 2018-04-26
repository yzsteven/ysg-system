package com.api.service;

import com.api.model.Response;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
public interface IndexService {

    public Response index(String cid);

    public Response queryAdvertisement(String cid,int type);

    public Response queryGoodDetail(Long id);

    public Response queryCategory(String cid);
}
