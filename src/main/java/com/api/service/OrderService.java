package com.api.service;

import com.api.model.Order;
import com.api.model.Response;

/**
 * Created by zy on 2018/3/8.
 */
public interface OrderService {

    public Response createOrder(Order order);
}
