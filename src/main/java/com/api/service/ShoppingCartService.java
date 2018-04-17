package com.api.service;

import com.api.model.Response;
import com.api.model.ShoppingCart;

/**
 * Created by zhou_yanga on 2018/4/16.
 */
public interface ShoppingCartService {

    public Response addShoppingCart(ShoppingCart record);

    public Response deleShoppingCart(String ids);

    public Response queryShoppingCart(ShoppingCart record);
}
