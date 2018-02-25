package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CompanyMapper;
import com.dao.PositionMapper;
import com.model.Company;
import com.service.CompanyService;
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
