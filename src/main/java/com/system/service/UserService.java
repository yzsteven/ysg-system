package com.system.service;

import java.util.List;
import java.util.Set;

import com.system.model.PageHelper;
import com.system.model.User;

public interface UserService {
	
	User queryUserByUserName(String username);

	Set<String> queryUserPermissions(String username);
	
	Set<String> queryUserRoles(String username);
	
	List<User> queryUsersAll(PageHelper pageHelper);
	
	int countUserAll(PageHelper pageHelper);
	
	String delUser(String username);
	
	String doAddUser(User user);
	
	String doModify(User user);
}
