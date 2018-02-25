package com.system.service;

import java.util.List;

import com.system.model.Company;

public interface CompanyService {

	public List<Company> getCompanyList();

	public Company searchCompanyInfo(long id);
}
