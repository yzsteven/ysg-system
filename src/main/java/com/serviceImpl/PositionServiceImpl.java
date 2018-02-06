package com.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PositionMapper;
import com.model.Position;
import com.service.PositionService;
@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionMapper positionMapper;
	
	public List<Position> getPositionList(Long companyId) {
		return positionMapper.selectPositionList(companyId);
	}

}
