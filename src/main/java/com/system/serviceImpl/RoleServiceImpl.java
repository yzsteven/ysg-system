package com.system.serviceImpl;

import java.util.HashMap;
import java.util.List;

import com.system.dao.ResourceMapper;
import com.system.model.*;
import com.system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.RoleMapper;
import com.system.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired 
	private RoleMapper roleMapper;

	@Autowired
	private	ResourceMapper resourceMapper;

	@Autowired
	private CompanyService companyService;
	
	public List<Role> selectRolesAll(Long companyId) {
		return roleMapper.selectRolesAll(companyId);
	}

	public List<Role> searchRoles(PageHelper pageHelper) {
		int pageSize = pageHelper.getPageSize();
		int pageNumber = pageHelper.getPageNumber();
		int	start = (pageNumber - 1) * pageSize;
		int end = pageNumber * pageSize;

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("end", end);
		param.put("search", pageHelper.getSearchParam());
		List<Role> roleList = roleMapper.selectRoles(param);
		if(roleList != null && roleList.size()>0){
			for (Role r : roleList) {
				String resourceIds = r.getResourceIds();
				StringBuffer sb = new StringBuffer();
				if(resourceIds != null && resourceIds != ""){
					for (String resourceId : resourceIds.split(",")) {
						Resource resource = resourceMapper.selectByPrimaryKey(Long.valueOf(resourceId));
						sb.append(resource.getName());
						sb.append(",");
					}
				}
				r.setResourceIds(sb.toString());
			}
		}
		return roleList;
	}

	/**
	 *新增角色
	 * @param role
	 * @return
	 */
	public String addRole(Role role) {
		String result = "fail";
		int count = roleMapper.insertSelective(role);
		if(count > 0){
			result = "success";
		}
		return result;
	}

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public String editRole(Role role) {
		String result = "fail";
		int count = roleMapper.updateByPrimaryKey(role);
		if(count > 0){
			result = "success";
		}
		return result;
	}

	/**
	 * 查询角色信息
	 * @param id
	 * @return
	 */
	public Role queryRoleInfo(long id){
		return roleMapper.selectByPrimaryKey(id);
	}

	public int countRoleAll(PageHelper pageHelper) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", pageHelper.getSearchParam());
		return roleMapper.selectCountRoleAll(param);
	}

	/**
	 * 删除角色
	 */
	public String delRole(long id) {
		Role record = new Role();
		record.setId(id);
		record.setAvailable(1);
		int count = roleMapper.updateByPrimaryKeySelective(record);
		String result = "fail";
		if(count > 0){
			result = "success";
		}
		return result;
	}
}
