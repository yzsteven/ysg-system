package com.system.service;

import java.util.List;

import com.system.model.Department;

public interface DepartmentService {
	
	public List<Department> getDepartmentList(Long companyId);

}
