package com.api.service;

import com.api.model.Order;
import com.api.model.Response;
import com.api.model.WXinfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zy on 2018/5/19.
 */
public interface WXInfoService {

    public WXinfo queryWXInfoByCid(String cid);

    public Response queryPayPackage(Order order);

    public String pay_notify(HttpServletRequest request);
}
