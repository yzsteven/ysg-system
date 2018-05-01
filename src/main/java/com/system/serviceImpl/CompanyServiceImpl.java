package com.system.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.api.model.Response;
import com.api.model.ResultCode;
import com.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.CompanyMapper;
import com.system.model.Company;
import com.system.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	public List<Company> getCompanyList() {
		return companyMapper.selectCompanyList();
	}

	public Company searchCompanyInfo(long id){
		return companyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 后台获取公司列表
	 * @param company
	 * @return
	 */
	public Response queryCompanyListByAdmin(Company company) {
		HashMap<String,Object> param = new HashMap<String, Object>();
		if(company.getPageHelper().getPageSize() != 0 && company.getPageHelper().getPageNumber() != 0){//需要进行分页
			//判断是否需要分页并设置分页参数
			int start = (company.getPageHelper().getPageNumber() - 1) * company.getPageHelper().getPageSize();
			param.put("start",start);
			param.put("num",company.getPageHelper().getPageSize());
		}
		if(!StringUtil.isBlank(company.getPageHelper().getSearchParam())){
			param.put("search",company.getPageHelper().getSearchParam());
		}
		List<HashMap<String,Object>> companyList = companyMapper.selectCompany(param);
		return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),companyList);
	}

	/**
	 * 后台获取公司总数
	 * @param company
	 * @return
	 */
	public int countCompanyListAll(Company company) {
		HashMap<String,Object> param = new HashMap<String, Object>();
		if(!StringUtil.isBlank(company.getPageHelper().getSearchParam())){
			param.put("search",company.getPageHelper().getSearchParam());
		}
		return companyMapper.selectCountCompanyAll(param);
	}

	public Response deleCompany(long id) {
		String result = "success";
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		Company company = new Company();
		company.setId(id);
		company.setIsdel(1);
		company.setUpdateTime(new Date());
		company.setUpdateBy(user.getUsername());
		int count = companyMapper.updateByPrimaryKeySelective(company);
		if(count <= 0){
			result = "fail";
		}
		return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
	}

	public Response doEditCompany(Company company) {
		String result = "success";
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		company.setUpdateTime(new Date());
		company.setUpdateBy(user.getUsername());
		int count = companyMapper.updateByPrimaryKeySelective(company);
		if(count <= 0){
			result = "fail";
		}
		return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
	}

	public Response doAddCompany(Company company) {
		String result = "success";
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		company.setIsdel(0);
		company.setCreateTime(new Date());
		company.setCreateBy(user.getUsername());
		int count = companyMapper.insertSelective(company);
		if(count <= 0){
			result = "fail";
		}
		return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
	}
}
