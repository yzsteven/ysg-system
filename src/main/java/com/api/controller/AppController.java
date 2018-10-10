package com.api.controller;

import com.api.model.*;
import com.api.service.*;
import com.system.model.Company;
import com.system.model.PageHelper;
import com.system.model.Resource;
import com.system.model.User;
import com.system.service.CompanyService;
import com.system.service.ResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

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

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/index")
    @ResponseBody
    public Response index(@RequestParam String cid) {
        return indexService.index(cid);
    }


    @RequestMapping("/list")
    @ResponseBody
    public Response queryAdvertisement(@RequestParam String cid, @RequestParam int type) {
        return indexService.queryAdvertisement(cid, type);
    }

    @RequestMapping("/details")
    @ResponseBody
    public Response queryGoodDetail(@RequestParam long id) {
        return indexService.queryGoodDetail(id);
    }


    @RequestMapping("/category")
    @ResponseBody
    public Response queryCategory(@RequestParam String cid) {
        return indexService.queryCategory(cid);
    }

    @RequestMapping("/createOrder")
    @ResponseBody
    public Response createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    /**
     * 后台获取订单列表
     *
     * @param order
     * @return
     */
    @RequestMapping("/queryOrderListByCID")
    @ResponseBody
    public Response queryOrderListByCID(@RequestBody Order order) {
        return orderService.queryOrderListByCID(order);
    }

    @RequestMapping("/queryOrderListByUID")
    @ResponseBody
    public Response queryOrderListByUID(@RequestBody Order order) {
        return orderService.queryOrderListByUID(order);
    }

    @RequestMapping("/modifyOrderInfo")
    @ResponseBody
    @RequiresPermissions("order:update")
    public Response modifyOrderInfo(@RequestBody Order order) {
        return orderService.modifyOrderInfo(order);
    }

    @RequestMapping("/queryOrderInfo")
    @RequiresPermissions("order:view")
    public ModelAndView modifyOrderInfo(@RequestParam Long id) {
        HashMap<String,Object> orderInfo =  orderService.queryOrderInfoById(id);
        return new ModelAndView("order_detail").addObject("order",orderInfo);
    }

    @RequestMapping("/modifyOrderInfoByAdmin")
    @ResponseBody
    @RequiresPermissions("order:update")
    public Response modifyOrderInfoByAdmin(@ModelAttribute Order order) {
        return orderService.modifyOrderInfoByAdmin(order);
    }

    @RequestMapping("/addShoppingCart")
    @ResponseBody
    public Response addShoppingCart(@RequestBody ShoppingCart record) {
        return shoppingCartService.addShoppingCart(record);
    }

    /**
     * 删除购物车商品
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleShoppingCart")
    @ResponseBody
    public Response deleShoppingCart(@RequestParam String ids) {
        return shoppingCartService.deleShoppingCart(ids);
    }

    @RequestMapping("/queryShoppingCart")
    @ResponseBody
    public Response queryShoppingCart(@RequestBody ShoppingCart record) {
        return shoppingCartService.queryShoppingCart(record);
    }

    /**
     * 后台获取分类列表
     *
     * @param pageHelper
     * @return
     */
    @RequestMapping("/queryCategoryList")
    @ResponseBody
    @RequiresPermissions("category:view")
    public HashMap<String, Object> queryCategoryList(@ModelAttribute PageHelper pageHelper) {
        Category category = new Category();
        HashMap<String, Object> result = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        category.setCid(user.getCompany());
        category.setPageHelper(pageHelper);
        Response resp = categoryService.queryCategory(category);
        int count = categoryService.countCategoryAll(category);
        int page = (count + pageHelper.getPageSize() - 1) / pageHelper.getPageSize();
        result.put("total", count);
        result.put("page", page);
        result.put("rows", resp.getRetValue());
        return result;
    }

    /**
     * 后台获取订单列表
     *
     * @param order
     * @return
     */
    @RequestMapping("/queryOrderList")
    @ResponseBody
    @RequiresPermissions("order:view")
    public HashMap<String, Object> queryOrderList(@RequestBody Order order) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        order.setCid(user.getCompany());
        Response resp = orderService.queryOrderListByCID(order);
        int count = orderService.countOrderListAll(order);
        int page = (count + order.getPageHelper().getPageSize() - 1) / order.getPageHelper().getPageSize();
        result.put("total", count);
        result.put("page", page);
        result.put("rows", resp.getRetValue());
        return result;
    }

    @RequestMapping("/toOrderSearch")
    @RequiresPermissions("order:view")
    public ModelAndView toOrderSearch() {
        return new ModelAndView("order_search");
    }

    @RequestMapping("/toAddCategory")
    @RequiresPermissions("category:create")
    public ModelAndView toAddCategory() {
        return new ModelAndView("category_add");
    }


    @RequestMapping("/toSearchCategory")
    @RequiresPermissions("category:view")
    public ModelAndView toSearchCategory() {
        return new ModelAndView("category_search");
    }

    /**
     * 后台添加分类
     *
     * @param category
     * @return
     */
    @RequestMapping("/addCategory")
    @ResponseBody
    @RequiresPermissions("category:create")
    public Response addCategory(@ModelAttribute Category category) {
        return categoryService.addCategory(category);
    }


    /**
     * 后台修改分类列表
     *
     * @param category
     * @return
     */
    @RequestMapping("/modifyCategory")
    @ResponseBody
    @RequiresPermissions("category:update")
    public Response modifyCategory(@ModelAttribute Category category) {
        return categoryService.modifyCategory(category);
    }

    @RequestMapping("/toModifyCategory")
    @RequiresPermissions("category:update")
    public ModelAndView toModifyCategory(@RequestParam long id) {
        Category category = categoryService.queryCategoryInfoById(id);
        return new ModelAndView("category_edit").addObject("category", category);
    }

    @RequestMapping("/deleCategory")
    @ResponseBody
    @RequiresPermissions("category:delete")
    public Response deleCategory(@RequestParam long id) {
        return categoryService.deleCategory(id);
    }

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "initPage", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> initPage() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Company> companyList = companyService.getCompanyList();
        List<Resource> resourceList = resourceService.queryResourceAll();
        result.put("companyList", companyList);
        result.put("resourceList", resourceList);
        return result;
    }

    /**
     * 初始化分类
     *
     * @return
     */
    @RequestMapping(value = "categoryInitPage", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> categoryInitPage() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        List<HashMap<String,Object>> categoryList = categoryService.queryCategoryListByCID(user.getCompany());
        result.put("categoryList", categoryList);
        return result;
    }

    /**
     * 后台获取商品列表
     *
     * @param good
     * @return
     */
    @RequestMapping("/queryGoodsListByCID")
    @ResponseBody
    @RequiresPermissions("good:view")
    public HashMap<String, Object> queryGoodsListByCID(@RequestBody Good good) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        good.setCid(user.getCompany());
        Response resp = goodService.queryGoodListByCID(good);
        int count = goodService.countGoodListAll(good);
        int page = (count + good.getPageHelper().getPageSize() - 1) / good.getPageHelper().getPageSize();
        result.put("total", count);
        result.put("page", page);
        result.put("rows", resp.getRetValue());
        return result;
    }

    @RequestMapping("/toGoodSearch")
    @RequiresPermissions("good:view")
    public ModelAndView toGoodSearch() {
        return new ModelAndView("good_search");
    }

    @RequestMapping("/deleGoodById")
    @ResponseBody
    @RequiresPermissions("good:delete")
    public Response deleGoodById(@RequestParam long id) {
        return goodService.deleGoodById(id);
    }

    @RequestMapping("/toAddGoods")
    @RequiresPermissions("good:create")
    public ModelAndView toAddGoods() {
        return new ModelAndView("good_add");
    }

    @RequestMapping("/doAddGoods")
    @ResponseBody
    @RequiresPermissions("good:create")
    public Response doAddGoods(@RequestBody Good good) {
        return goodService.doAddGoods(good);
    }

    @RequestMapping("/toModifyGoods")
    @RequiresPermissions("good:update")
    public ModelAndView toModifyGoods(@RequestParam long id) {
        HashMap<String,Object> result = goodService.queryGoodDetailForAdmin(id);
        return new ModelAndView("good_edit").addObject("goodInfo",result);
    }

    @RequestMapping("/doEditGoods")
    @ResponseBody
    @RequiresPermissions("good:update")
    public Response doEditGoods(@RequestBody Good good) {
        return goodService.editGoodsInfo(good);
    }

    @RequestMapping("/toAddBanner")
    @RequiresPermissions("banner:create")
    public ModelAndView toAddBanner() {
        return new ModelAndView("banner_add");
    }

    @RequestMapping("/doAddBanner")
    @ResponseBody
    @RequiresPermissions("banner:create")
    public Response doAddBanner(@ModelAttribute Banner banner){
        return bannerService.doAddBanner(banner);
    }

    @RequestMapping("/toEditBanner")
    @RequiresPermissions("banner:update")
    public ModelAndView toEditBanner(@RequestParam long id) {
        Banner banner = bannerService.queryBannerById(id);
        return new ModelAndView("banner_edit").addObject("banner",banner);
    }

    @RequestMapping("/doEditBanner")
    @ResponseBody
    @RequiresPermissions("banner:update")
    public Response doEditBanner(@ModelAttribute Banner banner){
        return bannerService.doEditBanner(banner);
    }

    @RequestMapping("deleBanner")
    @ResponseBody
    @RequiresPermissions("banner:delete")
    public Response deleBanner(@RequestParam long id){
        return bannerService.deleBanner(id);
    }


    /**
     * 后台获取轮播图列表
     *
     * @param pageHelper
     * @return
     */
    @RequestMapping("/queryBannerListByCID")
    @ResponseBody
    @RequiresPermissions("banner:view")
    public HashMap<String, Object> queryBannerListByCID(@ModelAttribute PageHelper pageHelper) {
        Banner banner = new Banner();
        HashMap<String, Object> result = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        banner.setCid(user.getCompany());
        banner.setPageHelper(pageHelper);
        Response resp = bannerService.queryBannerListByCID(banner);
        int count = bannerService.countBannerListAll(banner);
        int page = (count + pageHelper.getPageSize() - 1) / pageHelper.getPageSize();
        result.put("total", count);
        result.put("page", page);
        result.put("rows", resp.getRetValue());
        return result;
    }

    /**
     * 获取轮播图列表
     * @return
     */
    @RequestMapping("/toSearchBanner")
    @RequiresPermissions("banner:view")
    public ModelAndView toSearchBanner() {
        return new ModelAndView("banner_search");
    }


    public static void main(String[] args) {
        System.out.println("s");
    }
}
