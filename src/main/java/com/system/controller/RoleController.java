package com.system.controller;

import com.system.model.*;
import com.system.service.CompanyService;
import com.system.service.ResourceService;
import com.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/11.
 */
@RequestMapping("role")
@Controller
public class RoleController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toadd")
    @RequiresPermissions("role:create")
    public ModelAndView toadd(){
        return new ModelAndView("role_add");
    }

    @RequestMapping("/tosearch")
    @RequiresPermissions("role:view")
    public ModelAndView toSearch(){
        return new ModelAndView("role_search");
    }

    /**
     * 查询角色列表
     * @author zy
     * 2018年2月5日
     * 下午8:29:43
     * TODO
     */
    @RequestMapping(value="/searchRoles",method= RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("role:view")
    public HashMap<String, Object> searchRoles(@ModelAttribute PageHelper pageHelper){
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Role> roleList = roleService.searchRoles(pageHelper);
        int count = roleService.countRoleAll(pageHelper);
        int page = (count  +  pageHelper.getPageSize()  - 1) / pageHelper.getPageSize();
        result.put("total", count);
        result.put("page", page);
        result.put("rows", roleList);
        return result;
    }

    @RequestMapping(value = "initPage",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> initPage(){
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<Company> companyList = companyService.getCompanyList();
        List<Resource> resourceList = resourceService.queryResourceAll();
        result.put("companyList",companyList);
        result.put("resourceList",resourceList);
        return result;
    }

    @RequestMapping("doAddRole")
    @ResponseBody
    @RequiresPermissions("role:create")
    public String addRole(@ModelAttribute Role role){
        return  roleService.addRole(role);
    }


    @RequestMapping("/toedit")
    @RequiresPermissions("role:update")
    public ModelAndView toedit(@RequestParam long id){
        Role role = roleService.queryRoleInfo(id);
        return new ModelAndView("role_edit").addObject("role",role);
    }

    @RequestMapping("doEditRole")
    @ResponseBody
    @RequiresPermissions("role:update")
    public String doEditRole(@ModelAttribute Role role){
        return  roleService.editRole(role);
    }


    /**
     * 删除用户
     * @author zy
     * 2018年1月28日
     * 下午3:22:12
     * TODO
     */
    @RequestMapping(value="/delRole",method=RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:delete")
    public String delUser(@RequestParam long id){
        return roleService.delRole(id);
    }
}
