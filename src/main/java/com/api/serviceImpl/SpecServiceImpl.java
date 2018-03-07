package com.api.serviceImpl;

import com.api.dao.SpecMapper;
import com.api.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/3/1.
 */
@Service
public class SpecServiceImpl implements SpecService{

    @Autowired
    private SpecMapper specMapper;

    public List<HashMap<String,Object>> querySpecList(Long id){
        return specMapper.selectPriceByGid(id);
    }
}
