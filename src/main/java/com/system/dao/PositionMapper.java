package com.system.dao;

import java.util.List;

import com.system.model.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
    
    List<Position> selectPositionList(Long companyId);
}