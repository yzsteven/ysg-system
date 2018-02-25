package com.service;

import java.util.List;

import com.model.PageHelper;
import com.model.Resource;
import com.model.Role;

public interface RoleService {
	List<Role> selectRolesAll(Long companyId);

	List<Role> searchRoles(PageHelper pageHelper);

	String addRole(Role role);

	String editRole(Role role);

	String delRole(long id);

	Role queryRoleInfo(long id);

	int countRoleAll(PageHelper pageHelper);
}
