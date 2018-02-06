package com.dao;

import java.util.List;
import java.util.Set;

import com.model.Role;
import com.model.User;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    Set<String> selectUserRolesAll(String ids);
    
    List<Role> selectRolesAll(Long companyId);
}