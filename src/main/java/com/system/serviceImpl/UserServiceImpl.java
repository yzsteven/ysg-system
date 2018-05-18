package com.system.serviceImpl;

import java.util.*;

import com.system.util.CommonUtils;
import com.system.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.system.dao.ResourceMapper;
import com.system.dao.RoleMapper;
import com.system.dao.UserMapper;
import com.system.model.PageHelper;
import com.system.model.Role;
import com.system.model.User;
import com.system.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    public User queryUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    public List<User> queryUsersAll(PageHelper pageHelper) {
        int pageSize = pageHelper.getPageSize();
        int pageNumber = pageHelper.getPageNumber();
        int start = (pageNumber - 1) * pageSize;
        int end = pageSize;

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("end", end);
        param.put("search", pageHelper.getSearchParam());

        List<User> userList = userMapper.selectUsersAll(param);
        if (userList != null && userList.size() > 0) {
            for (User u : userList) {
                String roleIds = u.getRoleIds();
                StringBuffer sb = new StringBuffer();
                if (roleIds != null && roleIds != "") {
                    for (String roleid : roleIds.split(",")) {
                        Role role = roleMapper.selectByPrimaryKey(Long.valueOf(roleid));
                        if (role == null) {
                            continue;
                        }
                        sb.append(role.getDescription());
                        sb.append(",");
                    }
                }
                u.setRoleIds(sb.toString());
            }
        }
        return userList;
    }

    public Set<String> queryUserPermissions(String username) {
        User user = (User) SecurityUtils.getSubject().getSession()
                .getAttribute("user");
        Set<String> permissions = new HashSet<String>();
        for (String id : user.getRoleIds().split(",")) {
            // 获取角色权限id集合
            Role role = roleMapper.selectByPrimaryKey(Long.valueOf(id));
            if(role == null){
                continue;
            }
            for (String permisssionid : role.getResourceIds().split(",")) {
                permissions.add(resourceMapper.selectPermissions(Long
                        .valueOf(permisssionid)));
            }
        }
        return permissions;
    }

    public Set<String> queryUserRoles(String username) {
        User user = (User) SecurityUtils.getSubject().getSession()
                .getAttribute("user");
        String ids = user.getRoleIds();
        return roleMapper.selectUserRolesAll(ids);
    }


    public int countUserAll(PageHelper pageHelper) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("search", pageHelper.getSearchParam());
        return userMapper.selectCountUserAll(param);
    }

    /**
     * 冻结/解冻用户
     */
    public String delUser(String username) {
        User record = new User();
        record.setUsername(username);
        record.setLocked(1);
        record.setUpdateBy("");
        record.setUpdateTime(new Date());
        int count = userMapper.updateByUsernameSelective(record);
        String result = "fail";
        if (count > 0) {
            result = "success";
        }
        return result;
    }

    /**
     * @author zy
     * 2018年1月28日
     * 下午3:16:43
     * 新增用户
     */
    public String doAddUser(User user) {
        String result = "fail";
        User currentUser = (User) SecurityUtils.getSubject().getSession()
                .getAttribute("user");
        user.setCreateBy(currentUser.getUsername());
        user.setCreateTime(new Date());
        //根据配置的初始密码进行加密
        String oriPassword = CommonUtils.getProperties("origin_password");
        user.setPassword(oriPassword);
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        int count = userMapper.insertSelective(user);
        if (count > 0) {
            result = "success";
        }
        return result;
    }

    /**
     * 修改用户信息
     *
     * @author zy
     * 2018年2月5日
     * 下午9:01:11
     * TODO
     */
    public String doModify(User user) {
        String result = "fail";
        User currentUser = (User) SecurityUtils.getSubject().getSession()
                .getAttribute("user");
        user.setUpdateBy(currentUser.getUsername());
        user.setUpdateTime(new Date());
        if (!StringUtil.isBlank(user.getPassword())) {//修改的密码不为空
            user.setUsername(currentUser.getUsername());
            user.setPassword(user.getPassword());
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
        }
        int count = userMapper.updateByUsernameSelective(user);
        if (count > 0) {
            if (!StringUtil.isBlank(user.getPassword())) {//重置缓存密码
                currentUser.setPassword(user.getPassword());
                currentUser.setSalt(user.getSalt());
                SecurityUtils.getSubject().getSession().setAttribute("user",currentUser);
            }
            result = "success";
        }
        return result;
    }


    public static void main(String[] args) {
        String algorithmName = "MD5";
        //String salt = new SecureRandomNumberGenerator().nextBytes().toHex();//生成加密盐--随机数
        String salt = "994a1dab869bf4157a0f82aa3408bc28";
        int hashIterations = 2;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("zy",
                "123456", ByteSource.Util.bytes(salt), "MyRealm");
        SimpleHash hash = new SimpleHash(algorithmName, "123456", ByteSource.Util.bytes("zy" + salt), hashIterations);
        String encodedPassword = hash.toHex();
    }
}
