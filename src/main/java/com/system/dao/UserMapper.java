package com.system.dao;

import java.util.HashMap;
import java.util.List;

import com.system.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(User record);
    
    int updateByUsernameSelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectUserByUserName(String username);
    
    List<User> selectUsersAll(HashMap<String,Object> param);
    
    int selectCountUserAll(HashMap<String,Object> param);
}