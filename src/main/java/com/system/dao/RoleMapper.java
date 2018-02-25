package com.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.system.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    Set<String> selectUserRolesAll(String ids);
    
    List<Role> selectRolesAll(Long companyId);

    List<Role> selectRoles(HashMap<String, Object> param);//角色查询

    int selectCountRoleAll(HashMap<String, Object> param);
}