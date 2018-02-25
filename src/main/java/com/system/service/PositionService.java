package com.system.service;

import java.util.List;

import com.system.model.Position;

public interface PositionService {

	public List<Position> getPositionList(Long companyId);
}
