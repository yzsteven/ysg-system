package com.api.controller;

import com.api.model.Order;
import com.api.model.Response;
import com.api.service.IndexService;
import com.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
@Controller
@RequestMapping("/shop")
public class AppController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/index")
    @ResponseBody
    public Response index(@RequestParam Long cid){
        return indexService.index(cid);
    }


    @RequestMapping("/list")
    @ResponseBody
    public Response queryAdvertisement(@RequestParam Long cid, @RequestParam int type){
        return indexService.queryAdvertisement(cid,type);
    }

    @RequestMapping("/details")
    @ResponseBody
    public Response queryGoodDetail(@RequestParam long id){
        return indexService.queryGoodDetail(id);
    }


    @RequestMapping("/category")
    @ResponseBody
    public Response queryCategory(@RequestParam Long cid){
        return indexService.queryCategory(cid);
    }

    @RequestMapping("/createOrder")
    @ResponseBody
    public Response createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @RequestMapping("/queryOrderListByCID")
    @ResponseBody
    public Response queryOrderListByCID(@RequestBody Order order){
        return orderService.queryOrderListByCID(order);
    }

    @RequestMapping("/queryOrderListByUID")
    @ResponseBody
    public Response queryOrderListByUID(@RequestBody Order order){
        return orderService.queryOrderListByUID(order);
    }

    @RequestMapping("/modifyOrderInfo")
    @ResponseBody
    public Response modifyOrderInfo(@RequestBody Order order){
        return orderService.modifyOrderInfo(order);
    }
}
