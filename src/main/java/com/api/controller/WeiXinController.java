package com.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.model.*;
import com.api.service.OrderService;
import com.api.service.WXInfoService;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.RetryUtils;
import com.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by zy on 2018/5/10.
 */
@RequestMapping("/wx")
@Controller
public class WeiXinController {

    @Autowired
    private WXInfoService wXinfoService;

    @Autowired
    private OrderService orderService;

    private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();

    @RequestMapping("/queryOpenId")
    @ResponseBody
    public String queryOpenId(@RequestParam String code, @RequestParam String cid) {

        WXinfo wXinfo = wXinfoService.queryWXInfoByCid(cid);
        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?";
        //请求参数
        String params = "appid=" + wXinfo.getAppid() + "&secret=" + wXinfo.getAppSecret() + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpUtils.get(requestUrl + params);
        JSONObject job = JSON.parseObject(data);
        String openId = (String) job.get("openid");
        return openId;
    }

    @RequestMapping("/queryPayPackageInfo")
    @ResponseBody
    public Response queryPayPackageInfo(@ModelAttribute Order order) {
        Order orderInfo = orderService.queryOrderById(order.getId());
        orderInfo.setOpenId(order.getOpenId());
        return wXinfoService.queryPayPackage(orderInfo);
    }

    @RequestMapping("/pay_notify")
    @ResponseBody
    public String pay_notify(HttpServletRequest request) {
        return wXinfoService.pay_notify(request);
    }

    @RequestMapping("queryAppData")
    @ResponseBody
    public Response queryAppData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        WXinfo wXinfo = wXinfoService.queryWXInfoByCid(user.getCompany());
        AccessToken accessToken = getAccessToken(wXinfo.getAppid(),wXinfo.getAppSecret());
        Map data_week = ParaMap.create("begin_date","20180521").put("end_date","20180521").getData();
        //Map data_month = ParaMap.create("begin_date",getMonthFirstAndEnd("first")).put("end_date",getMonthFirstAndEnd("end")).getData();
        Map<String, String> headers = ParaMap.create("content-type","application/json").getData();
        String json_week = HttpKit.post("https://api.weixin.qq.com/datacube/getweanalysisappidweeklyvisittrend?access_token="+accessToken.getAccessToken(), null, JsonKit.toJson(data_week),headers);
       // String json_month = HttpKit.post("https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend?access_token="+accessToken.getAccessToken(), null, JsonKit.toJson(data_month),headers);
        WxData weekData = JSON.parseObject(json_week,WxData.class);
        WxData monthData = JSON.parseObject(json_week,WxData.class);
        HashMap<String,Object> result = new HashMap<String, Object>();
        result.put("weekData",weekData);
        result.put("monthData",monthData);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    public AccessToken getAccessToken(String appId,String appSecret) {
        AccessToken result = (AccessToken)accessTokenCache.get(appId);
        if(result != null && result.isAvailable()) {
            return result;
        } else {
            refreshAccessToken(appId,appSecret);
            return (AccessToken)accessTokenCache.get(appId);
        }
    }

    public  synchronized void refreshAccessToken(String appId,String appSecret) {
        final Map queryParas = ParaMap.create("appid", appId).put("secret", appSecret).getData();
        AccessToken result = (AccessToken) RetryUtils.retryOnException(3, new Callable() {
            public AccessToken call() throws Exception {
                String json = HttpUtils.get(url, queryParas);
                return new AccessToken(json);
            }
        });
        accessTokenCache.set(appId, result);
    }

    /*private static String getMonthFirstAndEnd(String type){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String firstday, lastday;

        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());

        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());

        if("first".equals(type)){
            return firstday;
        }else{
            return lastday;
        }
    }


    private static String getWeekFirstAndEnd(String type){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String firstday, lastday;

        // 获取本周的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.WEDNESDAY, 0);
        cale.set(Calendar.DAY_OF_WEEK, 1);
        firstday = format.format(cale.getTime());

        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.WEDNESDAY, 1);
        cale.set(Calendar.DAY_OF_WEEK, 0);
        lastday = format.format(cale.getTime());

        if("first".equals(type)){
            return firstday;
        }else{
            return lastday;
        }
    }*/

    public static void main(String[] args) {
        System.out.println("s");
    }

}
