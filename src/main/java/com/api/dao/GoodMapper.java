package com.api.dao;


import com.api.model.Good;

public interface GoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Good record);

    int insertSelective(Good record);

    Good selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);
}