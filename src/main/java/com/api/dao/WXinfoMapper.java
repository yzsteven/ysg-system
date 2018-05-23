package com.api.dao;

import com.api.model.WXinfo;

public interface WXinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WXinfo record);

    int insertSelective(WXinfo record);

    WXinfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WXinfo record);

    int updateByPrimaryKey(WXinfo record);

    WXinfo selectWXInfoByCid(String cid);
}