package com.system.controller;


import com.system.util.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.model.User;
@Controller
public class IndexController {

	private String algorithmName = "md5";
	private final int hashIterations = 2;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav =  new ModelAndView("/index");
		Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        String dbPassword = user.getPassword();
        //判断用户当前密码是不是初始密码
		String oriPassword = CommonUtils.getProperties("origin_password");
		String entryPassword = new SimpleHash(
				algorithmName,
				oriPassword,
				ByteSource.Util.bytes(user.getUsername() + user.getSalt()),
				hashIterations).toHex();
		boolean ifShow = false;
		if(dbPassword.equals(entryPassword)){//当前用户为初始密码弹出弹窗
			ifShow = true;
		}
		return mav.addObject("user",user).addObject("ifShow",ifShow);
	}

}
