package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestParam;

import com.model.PageHelper;
import com.model.Role;
import com.model.User;

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
