package com.api.serviceImpl;

import com.api.dao.OrderGoodsMapper;
import com.api.dao.OrderMapper;
import com.api.model.*;
import com.api.service.OrderService;
import com.system.model.User;
import com.system.util.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        String result = "success";
        String createBy = "api";
        String orderNo = CommonUtils.getOrderNo();
        order.setOrderno(orderNo);
        order.setIsdel(0);
        order.setCreateTime(new Date());
        int countOrder = orderMapper.insertSelective(order);
        if(countOrder <= 0 ){
            result = "fail";
            resultMap.put("result",result);
            return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),resultMap);
        }
        for(OrderGoods o : order.getOrderGoods()){
            o.setOrderid(order.getId());
            o.setCreateBy(createBy);
            int countOrderGoods = orderGoodsMapper.insertSelective(o);
            if(countOrderGoods <= 0 ){
                throw new IUFailException();
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
        order.setUpdateBy("api");
        order.setUpdateTime(new Date());
        int countOrder = orderMapper.updateByPrimaryKeySelective(order);
        if(countOrder <= 0 ){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     * 后台修改订单
     * @param order
     * @return
     */
    public Response modifyOrderInfoByAdmin(Order order) {
        String result = "success";
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        order.setUpdateBy(user.getUsername());
        order.setUpdateTime(new Date());
        switch (order.getOrderstatus()){
            case 1 : order.setOrderstatus(5);break;
            case 2 : order.setOrderstatus(3);break;
            case 3 : order.setOrderstatus(4);break;
        }
        int countOrder = orderMapper.updateByPrimaryKeySelective(order);
        if(countOrder <= 0 ){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    public int countOrderListAll(Order order) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        HashMap<String,Object> result = new HashMap<String, Object>();

        checkIfPaging(order,param);

        param.put("cid",order.getCid());

        if(order.getOrderstatus() != null){
            param.put("orderstatus",order.getOrderstatus());
        }

        if(!StringUtil.isBlank(order.getStartTime()) && !StringUtil.isBlank(order.getEndTime())){
            param.put("startTime",order.getStartTime());
            param.put("endTime",order.getEndTime());
        }

        if(order.getPageHelper().getPageSize() != 0 && order.getPageHelper().getPageNumber() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (order.getPageHelper().getPageNumber() - 1) * order.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",order.getPageHelper().getPageSize());
        }

        if(!StringUtil.isBlank(order.getPageHelper().getSearchParam())){
            param.put("search",order.getPageHelper().getSearchParam());
        }

        int count = orderMapper.selectCountOrderList(param);
        return count;
    }

    /**
     * 根据cid获取订单列表
     * @param order
     * @return
     */
    public Response queryOrderListByCID(Order order) {

        HashMap<String,Object> param = new HashMap<String, Object>();
        checkIfPaging(order,param);

        param.put("cid",order.getCid());

        if(order.getOrderstatus() != null){
            param.put("orderstatus",order.getOrderstatus());
        }

        if(!StringUtil.isBlank(order.getStartTime()) && !StringUtil.isBlank(order.getEndTime())){
            param.put("startTime",order.getStartTime());
            param.put("endTime",order.getEndTime());
        }

        if(order.getPageHelper().getPageSize() != 0 && order.getPageHelper().getPageNumber() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (order.getPageHelper().getPageNumber() - 1) * order.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",order.getPageHelper().getPageSize());
        }

        if(!StringUtil.isBlank(order.getPageHelper().getSearchParam())){
            param.put("search",order.getPageHelper().getSearchParam());
        }

        List<HashMap<String,Object>> orderList = orderMapper.selectOrderListByCID(param);

        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),orderList);
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

        if(order.getPageHelper().getPageSize() != 0 && order.getPageHelper().getPageNumber() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (order.getPageHelper().getPageNumber() - 1) * order.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",order.getPageHelper().getPageSize());
        }

    }



}
