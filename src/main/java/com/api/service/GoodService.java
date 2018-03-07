package com.api.service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
public interface GoodService {

    public List<HashMap<String,Object>> queryGoodsList(Long cid, int type);

    public HashMap<String,Object> queryGoodDetail(Long id);

    public List<HashMap<String,Object>> queryGoodListByCategory(Long id);
}
