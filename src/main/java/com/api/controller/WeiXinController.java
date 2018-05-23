package com.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.model.Order;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.model.WXinfo;
import com.api.service.OrderService;
import com.api.service.WXInfoService;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
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
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        WXinfo wXinfo = wXinfoService.queryWXInfoByCid(user.getCompany());
        AccessToken accessToken = getAccessToken(wXinfo.getAppid(),wXinfo.getAppSecret());
        Map data = ParaMap.create("begin_date","20180521").put("end_date","20180521").getData();
        Map<String, String> headers = ParaMap.create("content-type","application/json").getData();
        String json = HttpKit.post("https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend?access_token="+accessToken.getAccessToken(), null, JsonKit.toJson(data),headers);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),json);
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


}
