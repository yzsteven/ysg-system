package com.service;

import java.util.List;

import com.model.Position;

public interface PositionService {

	public List<Position> getPositionList(Long companyId);
}
