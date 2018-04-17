package com.api.serviceImpl;

import com.api.dao.ShoppingCartMapper;
import com.api.model.IUFailException;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.model.ShoppingCart;
import com.api.service.ShoppingCartService;
import org.apache.shiro.crypto.hash.Hash;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/4/16.
 */
@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 添加购物车
     * @param record
     * @return
     */
    public Response addShoppingCart(ShoppingCart record) {
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        String result = "success";
        //获取当前用户当前购物车中该商品的数量
        int num = shoppingCartMapper.selectGoodsNum(record);
        if(num > 0){//存在则更新数量
            record.setUpdateTime(new Date());
            record.setVersion(num);
            if(shoppingCartMapper.updateGoodsNum(record) <= 0){
                result = "fail";
            }
        }else{//不存在则插入
            record.setIsdel(0);
            record.setCreateTime(new Date());
            if(shoppingCartMapper.insert(record) <= 0){
                result = "fail";
            }
        }
        resultMap.put("result",result);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),resultMap);
    }

    /**
     * 删除购物车商品
     * @param ids
     * @return
     */
    public Response deleShoppingCart(String ids) {

        if(StringUtil.isBlank(ids)){
            return new Response(ResultCode.PARAMETERERROR.getCode(),ResultCode.PARAMETERERROR.getMsg());
        }

        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        String result = "success";
        ShoppingCart record = new ShoppingCart();
        for(String id : ids.split(",")){
            record.setId(Long.valueOf(id));
            record.setIsdel(1);
            record.setUpdateTime(new Date());
            if(shoppingCartMapper.updateByPrimaryKeySelective(record) <= 0){
                throw new IUFailException();
            }
        }
        resultMap.put("result",result);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),resultMap);
    }

    /**
     * 查询获取购物车列表
     * @param record
     * @return
     */
    public Response queryShoppingCart(ShoppingCart record) {
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        List<HashMap<String,Object>> glist = shoppingCartMapper.selectShoppinCart(record);
        resultMap.put("glist",glist);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),resultMap);
    }

}
