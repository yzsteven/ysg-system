package com.service;

import java.util.List;

import com.model.Department;

public interface DepartmentService {
	
	public List<Department> getDepartmentList(Long companyId);

}
