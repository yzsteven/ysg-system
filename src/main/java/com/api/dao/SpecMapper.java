package com.api.dao;


import com.api.model.Spec;

import java.util.HashMap;
import java.util.List;

public interface SpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Spec record);

    int insertSelective(Spec record);

    Spec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Spec record);

    int updateByPrimaryKey(Spec record);

    List<HashMap<String,Object>> selectPriceByGid(Long id);

    int updateByGid(HashMap<String,Object> param);
}