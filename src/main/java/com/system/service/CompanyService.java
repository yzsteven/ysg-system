package com.system.service;

import java.util.List;

import com.api.model.Response;
import com.system.model.Company;

public interface CompanyService {

	public List<Company> getCompanyList();

	public Company searchCompanyInfo(long id);

	public Response queryCompanyListByAdmin(Company company);

	public int countCompanyListAll(Company company);

	public Response deleCompany(long id);

	public Response doEditCompany(Company company);

	public Response doAddCompany(Company company);
}
