package com.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.dao.ResourceMapper;
import com.dao.RoleMapper;
import com.dao.UserMapper;
import com.model.PageHelper;
import com.model.Role;
import com.model.User;
import com.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ResourceMapper resourceMapper;

	public User queryUserByUserName(String username) {
		return userMapper.selectUserByUserName(username);
	}

	public List<User> queryUsersAll(PageHelper pageHelper) {
		int pageSize = pageHelper.getPageSize();
		int pageNumber = pageHelper.getPageNumber();
		int	start = (pageNumber - 1) * pageSize;
		int end = pageNumber * pageSize;

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("end", end);
		param.put("search", pageHelper.getSearchParam());

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		param.put("company", user.getCompany());
		List<User> userList = userMapper.selectUsersAll(param);
		if(userList != null && userList.size()>0){
			for (User u : userList) {
				String roleIds = u.getRoleIds();
				StringBuffer sb = new StringBuffer();
				if(roleIds != null && roleIds != ""){
					for (String roleid : roleIds.split(",")) {
						Role role = roleMapper.selectByPrimaryKey(Long.valueOf(roleid));
						sb.append(role.getDescription());
						sb.append(",");
					}
				}
				u.setRoleIds(sb.toString());
			}
		}
		return userList;
	}

	public Set<String> queryUserPermissions(String username) {
		User user = (User) SecurityUtils.getSubject().getSession()
				.getAttribute("user");
		Set<String> permissions = new HashSet<String>();
		for (String id : user.getRoleIds().split(",")) {
			// 获取角色权限id集合
			Role role = roleMapper.selectByPrimaryKey(Long.valueOf(id));
			for (String permisssionid : role.getResourceIds().split(",")) {
				permissions.add(resourceMapper.selectPermissions(Long
						.valueOf(permisssionid)));
			}
		}
		return permissions;
	}

	public Set<String> queryUserRoles(String username) {
		User user = (User) SecurityUtils.getSubject().getSession()
				.getAttribute("user");
		String ids = user.getRoleIds();
		return roleMapper.selectUserRolesAll(ids);
	}

	public int countUserAll(PageHelper pageHelper) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", pageHelper.getSearchParam());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		param.put("company", user.getCompany());
		return userMapper.selectCountUserAll(param);
	}

	/**
	 * 冻结/解冻用户
	 */
	public String delUser(String username) {
		User record = new User();
		record.setUsername(username);
		record.setLocked(1);
		int count = userMapper.updateByUsernameSelective(record);
		String result = "fail";
		if(count > 0){
			result = "success";
		}
		return result;
	}

	/**
	 * 
	 * @author zy
	 * 2018年1月28日
	 * 下午3:16:43
	 * 新增用户
	 */
	public String doAddUser(User user) {
		String result = "fail";
		//设置密码的初始MD5值
		int count = userMapper.insertSelective(user);
		if(count > 0){
			result = "success"; 
		}
		return result;
	}

	/**
	 * 修改用户信息
	 * @author zy
	 * 2018年2月5日
	 * 下午9:01:11
	 * TODO
	 */
	public String doModify(User user) {
		String result = "fail";
		int count = userMapper.updateByUsernameSelective(user);
		if(count > 0){
			result = "success";
		}
		return result;
	}

}
