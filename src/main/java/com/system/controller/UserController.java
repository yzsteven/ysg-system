package com.system.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.model.Company;
import com.system.model.Department;
import com.system.model.PageHelper;
import com.system.model.Position;
import com.system.model.Role;
import com.system.model.User;
import com.system.service.CompanyService;
import com.system.service.DepartmentService;
import com.system.service.PositionService;
import com.system.service.RoleService;
import com.system.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PositionService positionService;
	
	@RequestMapping("/search")
	@RequiresPermissions("user:view")
	public ModelAndView index(){
		ModelAndView mav =  new ModelAndView("/users_search");
		return mav;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("user:create")
	public ModelAndView add(){
		ModelAndView mav =  new ModelAndView("/users_add");
		List<Company> companyList = companyService.getCompanyList();
		mav.addObject("companyList", companyList);
		return mav;
	}
	
	/**
	 * 根据公司id获取对应的角色权限
	 * @author zy
	 * 2018年2月5日
	 * 下午8:30:03
	 * TODO
	 */
	@RequestMapping(value="/searchRoles",method=RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> searchroles(){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Role> roleList = roleService.selectRolesAll();
		result.put("roleList", roleList);
		return result;
	}
	
	/**
	 * 查询用户列表
	 * @author zy
	 * 2018年2月5日
	 * 下午8:29:43
	 * TODO
	 */
	@RequestMapping(value="/searchUsers",method=RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions("user:view")
	public HashMap<String, Object> searchUsers(@ModelAttribute PageHelper pageHelper){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<User> userList = userService.queryUsersAll(pageHelper);
		int count = userService.countUserAll(pageHelper);
		int page = (count  +  pageHelper.getPageSize()  - 1) / pageHelper.getPageSize();
		result.put("total", count);
		result.put("page", page);
		result.put("rows", userList);
		return result;
	}
	
	/**
	 * 新增用户
	 * @author zy
	 * 2018年1月28日
	 * 下午3:17:14
	 * TODO
	 */
	@RequestMapping(value="/doAddUser",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("user:create")
	public String  addUsers(@ModelAttribute User user){
		return userService.doAddUser(user);
	}
	
	/**
	 * 删除用户
	 * @author zy
	 * 2018年1月28日
	 * 下午3:22:12
	 * TODO
	 */
	@RequestMapping(value="/delUser",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("user:delete")
	public String delUser(@RequestParam String username){
		return userService.delUser(username);
	}
	
	/**
	 * 跳转编辑用户页面
	 * @author zy
	 * 2018年2月5日
	 * 下午8:30:54
	 * TODO
	 */
	@RequestMapping("toModify")
	@RequiresPermissions("user:update")
	public ModelAndView toModify(@RequestParam String username){
		User user  = userService.queryUserByUserName(username);
		List<Company> companyList = companyService.getCompanyList();
		return new ModelAndView("/users_edit").addObject("user",user).addObject("companyList", companyList);
	}


	/**
	 * 修改用户信息
	 * @author zy
	 * 2018年2月5日
	 * 下午8:30:54
	 * TODO
	 */
	@RequestMapping("doModify")
	@ResponseBody
	@RequiresPermissions("user:update")
	public String doModify(@ModelAttribute User user){
		return userService.doModify(user);
	}

}
