package com.api.controller;

import com.api.model.*;
import com.api.service.CategoryService;
import com.api.service.IndexService;
import com.api.service.OrderService;
import com.api.service.ShoppingCartService;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CategoryService categoryService;

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

    /**
     * 后台获取订单列表
     * @param order
     * @return
     */
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

    @RequestMapping("/addShoppingCart")
    @ResponseBody
    public Response addShoppingCart(@RequestBody ShoppingCart record){
        return shoppingCartService.addShoppingCart(record);
    }

    /**
     * 删除购物车商品
     * @param ids
     * @return
     */
    @RequestMapping("/deleShoppingCart")
    @ResponseBody
    public Response deleShoppingCart(@RequestParam String ids){
        return shoppingCartService.deleShoppingCart(ids);
    }

    @RequestMapping("/queryShoppingCart")
    @ResponseBody
    public Response queryShoppingCart(@RequestBody ShoppingCart record){
        return shoppingCartService.queryShoppingCart(record);
    }

    /**
     * 后台获取分类列表
     * @param category
     * @return
     */
    @RequestMapping("/queryCategoryList")
    @ResponseBody
    public Response queryCategoryList(@RequestBody Category category){
        return categoryService.queryCategory(category);
    }

    @RequestMapping("/toAddCategory")
    public ModelAndView toAddCategory(){
        return new ModelAndView("category_add");
    }

    /**
     * 后台添加分类
     * @param category
     * @return
     */
    @RequestMapping("/addCategory")
    @ResponseBody
    public Response addCategory(@ModelAttribute Category category){
        return categoryService.addCategory(category);
    }


    /**
     * 后台修改分类列表
     * @param category
     * @return
     */
    @RequestMapping("/modifyCategory")
    @ResponseBody
    public Response modifyCategory(@RequestBody Category category){
        return categoryService.modifyCategory(category);
    }
}
