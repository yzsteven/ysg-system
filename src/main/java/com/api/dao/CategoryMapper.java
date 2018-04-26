package com.api.dao;


import com.api.model.Category;

import java.util.HashMap;
import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    Category selectCategoryInfoById(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<HashMap<String,Object>> selectCategoryList(String cid);

    List<HashMap<String,Object>> selectCategory(HashMap<String,Object> param);

    int selectCountCategoryAll(HashMap<String,Object> param);
}