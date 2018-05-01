package com.system.dao;

import java.util.HashMap;
import java.util.List;

import com.system.model.Company;
import org.apache.shiro.crypto.hash.Hash;

public interface CompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    
    List<Company> selectCompanyList();

    List<HashMap<String,Object>> selectCompany(HashMap<String,Object> param);

    int selectCountCompanyAll(HashMap<String,Object> param);
}