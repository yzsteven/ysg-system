package com.system.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.PositionMapper;
import com.system.model.Position;
import com.system.service.PositionService;
@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionMapper positionMapper;
	
	public List<Position> getPositionList(Long companyId) {
		return positionMapper.selectPositionList(companyId);
	}

}
