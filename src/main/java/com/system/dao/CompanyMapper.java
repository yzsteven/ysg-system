package com.system.dao;

import java.util.List;

import com.system.model.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    
    List<Company> selectCompanyList();
}