package com.api.service;

import com.api.model.Good;
import com.api.model.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
public interface GoodService {

    public List<HashMap<String,Object>> queryGoodsList(String cid, int type);

    public HashMap<String,Object> queryGoodDetail(Long id);

    public List<HashMap<String,Object>> queryGoodListByCategory(Long id);

    public Response queryGoodListByCID(Good good);

    public int countGoodListAll(Good good);

    public Response deleGoodById(Long id);

    public Response doAddGoods(Good good);

    public Response editGoodsInfo(Good good);

    public HashMap<String,Object> queryGoodDetailForAdmin(Long id);
}
