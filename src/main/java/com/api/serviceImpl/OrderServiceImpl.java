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

import java.util.HashMap;
import java.util.List;
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


    /**
     * 修改订单
     * @param order
     * @return
     */
    public Response modifyOrderInfo(Order order) {
        String result = "success";
        int countOrder = orderMapper.updateByPrimaryKeySelective(order);
        if(countOrder <= 0 ){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     * 根据cid获取订单列表
     * @param order
     * @return
     */
    public Response queryOrderListByCID(Order order) {

        HashMap<String,Object> param = new HashMap<String, Object>();
        HashMap<String,Object> result = new HashMap<String, Object>();

        checkIfPaging(order,param);

        param.put("cid",order.getCid());
        param.put("orderstatus",order.getOrderstatus());

        List<HashMap<String,Object>> orderList = orderMapper.selectOrderListByCID(param);

        result.put("orderList",orderList);

        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     * 根据uid获取订单列表
     * @param order
     * @return
     */
    public Response queryOrderListByUID(Order order) {

        HashMap<String,Object> param = new HashMap<String, Object>();
        HashMap<String,Object> result = new HashMap<String, Object>();

        checkIfPaging(order,param);

        param.put("cid",order.getCid());
        param.put("createBy",order.getCreateBy());
        param.put("orderstatus",order.getOrderstatus());

        List<HashMap<String,Object>> orderList = orderMapper.selectOrderListByUid(param);

        if(orderList != null && orderList.size() > 0){
            for(HashMap<String,Object> orderMap : orderList){
                Long orderId = (Long) orderMap.get("id");
                List<HashMap<String,Object>> goodsList = orderGoodsMapper.selectGoodsListByOrderId(orderId);
                orderMap.put("goodsList",goodsList);
            }
        }

        result.put("orderList",orderList);

        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    public void checkIfPaging(Order order,HashMap<String,Object> param){

        if(order.getPageHelper().getPageSize() != 0 && order.getPageHelper().getPageIndex() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (order.getPageHelper().getPageIndex() - 1) * order.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",order.getPageHelper().getPageSize());
        }

    }



}
