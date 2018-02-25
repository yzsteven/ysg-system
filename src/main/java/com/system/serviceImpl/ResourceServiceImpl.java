package com.system.serviceImpl;

import com.system.dao.ResourceMapper;
import com.system.model.Resource;
import com.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhou_yanga on 2018/2/11.
 */
@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 获取所有权限
     * @return
     */
    public List<Resource> queryResourceAll() {
        return resourceMapper.selectPermissionsAll();
    }


}
