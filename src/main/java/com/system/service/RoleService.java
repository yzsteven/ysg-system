package com.system.service;

import java.util.List;

import com.system.model.PageHelper;
import com.system.model.Role;

public interface RoleService {
	List<Role> selectRolesAll();

	List<Role> searchRoles(PageHelper pageHelper);

	String addRole(Role role);

	String editRole(Role role);

	String delRole(long id);

	Role queryRoleInfo(long id);

	int countRoleAll(PageHelper pageHelper);
}
