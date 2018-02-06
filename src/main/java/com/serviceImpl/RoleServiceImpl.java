package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoleMapper;
import com.model.Role;
import com.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired 
	private RoleMapper roleMapper;
	
	public List<Role> selectRolesAll(Long companyId) {
		return roleMapper.selectRolesAll(companyId);
	}
}
