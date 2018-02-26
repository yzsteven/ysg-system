package com.dao;

import com.model.Spec;

public interface SpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Spec record);

    int insertSelective(Spec record);

    Spec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Spec record);

    int updateByPrimaryKey(Spec record);
}