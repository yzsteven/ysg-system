package com.api.serviceImpl;

import com.api.dao.CategoryMapper;
import com.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/3/5.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    public List<HashMap<String, Object>> queryCategoryList(Long cid) {
        return categoryMapper.selectCategoryList(cid);
    }

}
