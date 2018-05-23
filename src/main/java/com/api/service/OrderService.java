package com.api.service;

import com.api.model.Order;
import com.api.model.Response;

import java.util.HashMap;

/**
 * Created by zy on 2018/3/8.
 */
public interface OrderService {

    public Response createOrder(Order order);

    public Response queryOrderListByCID(Order order);

    public Response queryOrderListByUID(Order order);

    public Response modifyOrderInfo(Order order);

    public Response modifyOrderInfoByAdmin(Order order);

    public int countOrderListAll(Order order);

    public HashMap<String,Object> queryOrderInfoById(Long id);

    public Order queryOrderById(Long id);

    public Order queryOrderByOrderNo(String  orderNo);
}
