package com.api.dao;

import com.api.model.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<HashMap<String,Object>> selectOrderListByCID(HashMap<String,Object> param);

    List<HashMap<String,Object>> selectOrderListByUid(HashMap<String,Object> param);

    int selectCountOrderList(HashMap<String,Object> param);

    Order selectByOrderNo(String orderNo);

}