package com.api.serviceImpl;

import com.api.dao.CategoryMapper;
import com.api.model.Category;
import com.api.model.Response;
import com.api.model.ResultCode;
import com.api.service.CategoryService;
import com.api.service.GoodService;
import com.system.model.PageHelper;
import com.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.swing.StringUIClientPropertyKey;

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

    public List<HashMap<String, Object>> queryCategoryList(String cid) {
        List<HashMap<String,Object>> categoryList = categoryMapper.selectCategoryList(cid);
        return categoryList;
    }


    public Response queryCategory(Category category) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        if(category.getPageHelper().getPageSize() != 0 && category.getPageHelper().getPageNumber() != 0){//需要进行分页
            //判断是否需要分页并设置分页参数
            int start = (category.getPageHelper().getPageNumber() - 1) * category.getPageHelper().getPageSize();
            param.put("start",start);
            param.put("num",category.getPageHelper().getPageSize());
        }
        if(!StringUtil.isBlank(category.getPageHelper().getSearchParam())){
            param.put("search",category.getPageHelper().getSearchParam());
        }
        param.put("cid",category.getCid());
        List<HashMap<String,Object>> categoryList = categoryMapper.selectCategory(param);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),categoryList);
    }

    public Response addCategory(Category category) {
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        String result = "success";
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        category.setCid(user.getCompany());
        category.setIsdel(0);
        category.setCreateBy(user.getUsername());
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
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        category.setCid(user.getCompany());
        category.setUpdateBy(user.getUsername());
        category.setUpdateTime(new Date());
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if(count <= 0){
            result = "fail";
        }
        resultMap.put("result",result);
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),resultMap);
    }

    public int countCategoryAll(Category category) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("cid",category.getCid());
        if(!StringUtil.isBlank(category.getPageHelper().getSearchParam())){
            param.put("search",category.getPageHelper().getSearchParam());
        }
        return categoryMapper.selectCountCategoryAll(param);
    }

    /**
     *获取分类详情
     * @param id
     * @return
     */
    public Category queryCategoryInfoById(Long id) {
        Category category = categoryMapper.selectCategoryInfoById(id);
        return category;
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    public Response deleCategory(Long id) {
        String result = "success";
        Category category = new Category();
        category.setId(id);
        category.setIsdel(1);
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if(count <= 0){
            result = "fail";
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),result);
    }

    /**
     * 获取当前店铺所有的分类
     * @param cid
     * @return
     */
    public List<HashMap<String,Object>> queryCategoryListByCID(String cid) {
        return categoryMapper.selectCategoryList(cid);
    }


}
