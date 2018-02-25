package com.system.dao;

import com.system.model.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    List<Resource> selectPermissionsAll();
    
    String selectPermissions(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}