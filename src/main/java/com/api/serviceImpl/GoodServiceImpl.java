package com.api.serviceImpl;

import com.api.dao.GoodMapper;
import com.api.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
@Service
@Transactional
public class GoodServiceImpl implements GoodService{


    @Autowired
    private GoodMapper goodMapper;
    /**
     * 获取新品/推荐/精选 商品列表
     * @param cid
     * @param type 1 isnew 2 isrecommend 3isselected
     * @return
     */
    public List<HashMap<String,Object>> queryGoodsList(String cid, int type) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("cid",cid);
        switch (type){
            case 1 : param.put("isnew",1);break;
            case 2 : param.put("isrecommend",1);break;
            case 3 : param.put("isselected",1);break;
        }
        return goodMapper.selectGoodsList(param);
    }

}