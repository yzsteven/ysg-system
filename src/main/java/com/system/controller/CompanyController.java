package com.system.controller;

import com.api.model.Response;
import com.system.model.Company;
import com.system.model.PageHelper;
import com.system.model.User;
import com.system.service.CompanyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by zy on 2018/4/30.
 */
@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    /**
     * 后台获取公司列表
     *
     * @param pageHelper
     * @return
     */
    @RequestMapping("/queryCompanyList")
    @ResponseBody
    public HashMap<String, Object> queryCompanyList(@ModelAttribute PageHelper pageHelper) {
        Company company = new Company();
        HashMap<String, Object> result = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        company.setPageHelper(pageHelper);
        Response resp = companyService.queryCompanyListByAdmin(company);
        int count = companyService.countCompanyListAll(company);
        int page = (count + pageHelper.getPageSize() - 1) / pageHelper.getPageSize();
        result.put("total", count);
        result.put("page", page);
        result.put("rows", resp.getRetValue());
        return result;
    }

    /**
     * 获取轮播图列表
     * @return
     */
    @RequestMapping("/toSearchCompany")
    public ModelAndView toSearchCompany() {
        return new ModelAndView("company_search");
    }

    /**
     * 删除公司
     * @param id
     * @return
     */
    @RequestMapping("deleCompany")
    @ResponseBody
    public Response deleCompany(@RequestParam long id){
        return companyService.deleCompany(id);
    }


    @RequestMapping("/toEditCompany")
    public ModelAndView toEditBanner(@RequestParam long id) {
        Company company = companyService.searchCompanyInfo(id);
        return new ModelAndView("company_edit").addObject("company",company);
    }

    @RequestMapping("/doEditCompany")
    @ResponseBody
    public Response doEditBanner(@ModelAttribute Company company){
        return companyService.doEditCompany(company);
    }

    @RequestMapping("/toAddCompany")
    public ModelAndView toAddCompany() {
        return new ModelAndView("company_add");
    }

    @RequestMapping("/doAddCompany")
    @ResponseBody
    public Response doAddCompany(@ModelAttribute Company company){
        return companyService.doAddCompany(company);
    }



}
