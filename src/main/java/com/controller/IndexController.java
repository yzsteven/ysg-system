package com.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav =  new ModelAndView("/index");
		Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
		return mav.addObject("user",user);
	}

}
