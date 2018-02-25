package com.system.serviceImpl;

import java.util.List;

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
}
