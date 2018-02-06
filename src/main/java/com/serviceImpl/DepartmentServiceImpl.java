package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DepartmentMapper;
import com.dao.PositionMapper;
import com.model.Department;
import com.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDepartmentList(Long companyId) {
		return departmentMapper.selectDepartmentList(companyId);
	}

}
