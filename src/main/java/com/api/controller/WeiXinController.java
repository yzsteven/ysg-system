package com.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.system.util.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zy on 2018/5/10.
 */
@RequestMapping("/wx")
@Controller
public class WeiXinController {

    @RequestMapping("/queryOpenId")
    @ResponseBody
    public String queryOpenId(@RequestParam String code){

        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?";
        //请求参数
        String params = "appid=" + CommonUtils.getProperties("appid")+ "&secret=" + CommonUtils.getProperties("secret") + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpUtils.get(requestUrl+params);
        JSONObject job = JSON.parseObject(data);
        String openId = (String) job.get("openid");
        return openId;
    }
}
