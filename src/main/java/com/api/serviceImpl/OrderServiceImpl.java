package com.api.serviceImpl;

import com.api.dao.OrderGoodsMapper;
import com.api.dao.OrderMapper;
import com.api.model.Order;
import com.api.model.OrderGoods;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.service.OrderService;
import com.system.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by zy on 2018/3/8.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;


    /**
     *  创建订单
     * @param order
     * @return
     */
    public Response createOrder(Order order) {
        String result = "success";
        String createBy = "api";
        String orderNo = CommonUtils.getOrderNo();
        order.setOrderno(orderNo);
        order.setCreateBy(createBy);
        int countOrder = orderMapper.insertSelective(order);
        if(countOrder <= 0 ){
            result = "fail";
        }
        for(OrderGoods o : order.getOrderGoods()){
            o.setOrderid(order.getId());
            o.setCreateBy(createBy);
            int countOrderGoods = orderGoodsMapper.insertSelective(o);
            if(countOrderGoods <= 0 ){
                result = "fail";
                break;
            }
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }
}
