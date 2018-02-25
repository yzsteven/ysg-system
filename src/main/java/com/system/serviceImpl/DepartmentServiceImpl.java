package com.system.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.DepartmentMapper;
import com.system.model.Department;
import com.system.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDepartmentList(Long companyId) {
		return departmentMapper.selectDepartmentList(companyId);
	}

}
