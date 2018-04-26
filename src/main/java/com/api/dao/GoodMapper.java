package com.api.dao;


import com.api.model.Good;

import java.util.HashMap;
import java.util.List;

public interface GoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Good record);

    int insertSelective(Good record);

    Good selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);

    List<HashMap<String,Object>> selectGoodsList(HashMap<String,Object> param);

    HashMap<String,Object> selectGoodInfo(Long id);

    List<HashMap<String,Object>> selectGoodListByCategory(Long id);

    List<HashMap<String,Object>> selectGoodListByCID(HashMap<String,Object> param);

    int selectCountGoodList(HashMap<String,Object> param);

}