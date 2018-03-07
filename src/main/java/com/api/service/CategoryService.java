package com.api.service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/3/5.
 */
public interface CategoryService {

    public List<HashMap<String,Object>> queryCategoryList(Long cid);
}
