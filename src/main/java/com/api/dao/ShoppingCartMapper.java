package com.api.dao;

import com.api.model.ShoppingCart;
import org.apache.shiro.crypto.hash.Hash;

import java.util.HashMap;
import java.util.List;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int selectGoodsNum(ShoppingCart record);//获取该商铺当前用户的该商品数量

    int updateGoodsNum(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);

    List<HashMap<String,Object>> selectShoppinCart(ShoppingCart record);
}