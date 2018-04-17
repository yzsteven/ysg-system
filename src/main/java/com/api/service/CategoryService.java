package com.api.service;

import com.api.model.Category;
import com.api.model.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/3/5.
 */
public interface CategoryService {

    public List<HashMap<String,Object>> queryCategoryList(Long cid);

    public Response queryCategory(Category category);

    public Response addCategory(Category category);

    public Response modifyCategory(Category category);
}
