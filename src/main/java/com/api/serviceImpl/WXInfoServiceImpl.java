package com.api.serviceImpl;

import com.api.dao.WXinfoMapper;
import com.api.model.Order;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.model.WXinfo;
import com.api.service.OrderService;
import com.api.service.WXInfoService;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.system.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zy on 2018/5/19.
 */
@Service
@Transactional
public class WXInfoServiceImpl implements WXInfoService {

    @Autowired
    private WXinfoMapper wXinfoMapper;

    @Autowired
    private OrderService orderService;


    /**
     * 根据cid获取商户微信配置
     *
     * @param cid
     * @return
     */
    public WXinfo queryWXInfoByCid(String cid) {
        WXinfo wXinfo = wXinfoMapper.selectWXInfoByCid(cid);
        return wXinfo;
    }

    /**
     * 公众号支付获取签名参数
     */
    public Response queryPayPackage(Order order) {
        WXinfo wXinfo = wXinfoMapper.selectWXInfoByCid(order.getCid());
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", wXinfo.getAppid());
        params.put("mch_id", wXinfo.getMchId());
        params.put("body", "eshop支付");
        params.put("out_trade_no", order.getOrderno());
        BigDecimal totalFee = order.getPayprice().multiply(new BigDecimal(100));
        params.put("total_fee", String.valueOf(totalFee.intValue()));

        String ip = "127.0.0.1";

        params.put("spbill_create_ip", ip);
        params.put("trade_type", "JSAPI");
        params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
        params.put("notify_url", CommonUtils.getProperties("notify_url"));
        params.put("openid", order.getOpenId());
        params.put("attach", order.getCid());//附加字段为订单所属商户号

        String sign = PaymentKit.createSign(params, wXinfo.getMchKey());
        params.put("sign", sign);
        String xmlResult = PaymentApi.pushOrder(params);

        System.out.println(xmlResult);
        Map<String, String> result = PaymentKit.xmlToMap(xmlResult);

        String return_code = result.get("return_code");
        String return_msg = result.get("return_msg");
        if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
            return new Response(ResultCode.SYSTENERROR.getCode(), ResultCode.SYSTENERROR.getMsg());
        }
        String result_code = result.get("result_code");
        if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
            return new Response(ResultCode.SYSTENERROR.getCode(), ResultCode.SYSTENERROR.getMsg());
        }
        // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
        String prepay_id = result.get("prepay_id");

        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("appId", wXinfo.getAppid());
        packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        packageParams.put("nonceStr", System.currentTimeMillis() + "");
        packageParams.put("package", "prepay_id=" + prepay_id);
        packageParams.put("signType", "MD5");
        String packageSign = PaymentKit.createSign(packageParams, wXinfo.getMchKey());
        packageParams.put("paySign", packageSign);

        return new Response(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), packageParams);
    }

    /**
     * 支付成功通知
     */
    public String pay_notify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);

        String result_code = params.get("result_code");
        // 总金额
        String totalFee = params.get("total_fee");
        // 商户订单号
        String orderId = params.get("out_trade_no");
        // 微信支付订单号
        String transId = params.get("transaction_id");
        // 支付完成时间，格式为yyyyMMddHHmmss
        String timeEnd = params.get("time_end");

        String attach = params.get("attach");//订单所属商户号
        Order orderInfo = orderService.queryOrderByOrderNo(orderId);
        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 避免已经成功、关闭、退款的订单被再次更新
        WXinfo wXinfo = queryWXInfoByCid(attach);
        SortedMap<String, String> smap = new TreeMap<String, String>(params);
        boolean isWechatSign = isWechatSign(smap,wXinfo.getMchKey());
        if(!isWechatSign){//验签通过 继续校验价格
            return "";
        }
        boolean isRightPrice = validatePrice(orderInfo,totalFee);
        if(!isRightPrice){
            return "";
        }
        if (PaymentKit.verifyNotify(params, wXinfo.getMchKey())) {
            if (("SUCCESS").equals(result_code)) {
                //更新订单信息
                Order order  = new Order();
                order.setId(orderInfo.getId());
                order.setPaytype(1);
                order.setPayno(transId);
                order.setPaytime(new Date());
                order.setOrderstatus(2);
                Response resp = orderService.modifyOrderInfo(order);
                if(resp.getRetCode() == ResultCode.SUCCESS.getCode()){
                    Map<String, String> xml = new HashMap<String, String>();
                    xml.put("return_code", "SUCCESS");
                    xml.put("return_msg", "OK");
                    return PaymentKit.toXml(xml);
                }
            }
        }
        return "";
    }

    public static boolean isWechatSign(SortedMap<String, String> smap,String apiKey) {
        StringBuffer sb = new StringBuffer();
        Set es = smap.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        /** 验证的签名 */
        String sign = HashKit.md5(sb.toString()).toUpperCase();
        /** 微信端返回的合法签名 */
        String validSign = ((String) smap.get("sign")).toUpperCase();
        return validSign.equals(sign);
    }

    /**
     * 校验支付价格与数据库中的价格
     * @return
     */
    public boolean validatePrice(Order order,String totalFee){
        //比较实际支付金额与数据库中的支付金额
        BigDecimal dbPayPrice = order.getPayprice().multiply(new BigDecimal(100));
        BigDecimal notifyPayPrice = new BigDecimal(totalFee);
        if(dbPayPrice.compareTo(notifyPayPrice) != 0){
            return false;
        }
        return true;
    }

}
