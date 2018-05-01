package com.api.dao;

import com.api.model.Banner;

import java.util.HashMap;
import java.util.List;

public interface BannerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    List<Banner> selectBannerList(HashMap<String,Object> param);

    List<HashMap<String,Object>> selectBanner(HashMap<String,Object> param);

    int selectCountBannerAll(HashMap<String,Object> param);
}