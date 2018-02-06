package com.service;

import java.util.List;

import com.model.Role;

public interface RoleService {
	List<Role> selectRolesAll(Long companyId);
}
