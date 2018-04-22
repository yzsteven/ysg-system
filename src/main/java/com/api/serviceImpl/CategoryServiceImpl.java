package com.api.serviceImpl;

import com.api.dao.CategoryMapper;
import com.api.model.Category;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.service.CategoryService;
import com.api.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhou_yanga on 2018/3/5.
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    public List<HashMap<String, Object>> queryCategoryList(Long cid) {
        List<HashMap<String,Object>> categoryList = categoryMapper.selectCategoryList(cid);
        return categoryList;
    }


    public Response queryCategory(Category category) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        if(category.getPageHelper().getPageSize() != 0 && category.getPageHelper().getPageIndex() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (category.getPageHelper().getPageIndex() - 1) * category.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",category.getPageHelper().getPageSize());
        }
        param.put("cid",category.getCid());
        List<HashMap<String,Object>> categoryList = categoryMapper.selectCategory(param);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),categoryList);
    }

    public Response addCategory(Category category) {
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        String result = "success";
        category.setIsdel(0);
        category.setCreateTime(new Date());
        int count = categoryMapper.insertSelective(category);
        if(count <= 0){
            result = "fail";
        }
        resultMap.put("result",result);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),resultMap);
    }

    public Response modifyCategory(Category category) {
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        String result = "success";
        category.setUpdateBy("");
        category.setUpdateTime(new Date());
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if(count <= 0){
            result = "fail";
        }
        resultMap.put("result",resultMap);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),resultMap);
    }


}
