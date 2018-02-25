package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login") 
	public ModelAndView login() { 
		return new ModelAndView("login");
		
	}
	
	@RequestMapping(value = "/logout")  
	@RequiresAuthentication
	public ModelAndView logout() { 
		Subject subject = SecurityUtils.getSubject();  
		if (subject.isAuthenticated()) {  
		    subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存  
		}  
		return new ModelAndView("login");
		
	}

	@RequestMapping(value = "/dologin",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doLogin(@ModelAttribute User user) {  
	    HashMap<String, Object> result = new HashMap<String, Object>();
	    String msg = "";
	    String userName = user.getUsername();
	    String password = user.getPassword();

	    if(StringUtil.isBlank(user.getUsername())){
			msg = "用户名不能为空";
			result.put("result", "fail");
			result.put("msg", msg);
			return result;
		}

		if(StringUtil.isBlank(user.getPassword())){
			msg = "密码不能为空";
			result.put("result", "fail");
			result.put("msg", msg);
			return result;
		}


	    UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
	    Subject subject = SecurityUtils.getSubject();  
	    try {  
	        subject.login(token);  
	    } catch (IncorrectCredentialsException e) {  
	        msg = "登录密码错误"; 
	        result.put("result", "fail");
	        result.put("msg", msg);
	        return result; 
	    } catch (ExcessiveAttemptsException e) {  
	        msg = "登录失败次数过多";  
	        result.put("result", "fail");
	        result.put("msg", msg);
	        return result; 
	    } catch (LockedAccountException e) {  
	        msg = "帐号已被锁定";  
	        result.put("result", "fail");
	        result.put("msg", msg);
	        return result; 
	    } catch (DisabledAccountException e) {  
	        msg = "帐号已被禁用";  
	        result.put("result", "fail");
	        result.put("msg", msg);
	        return result; 
	    } catch (ExpiredCredentialsException e) {  
	        msg = "帐号已过期"; 
	        result.put("result", "fail");
	        result.put("msg", msg);
	        return result; 
	    } catch (UnknownAccountException e) {  
	        msg = "帐号不存在";  
	        result.put("result", "fail");
	        result.put("msg", msg);
	        return result; 
	    } catch (UnauthorizedException e) {  
	        msg = "您没有得到相应的授权"; 
	        result.put("result", "fail");
	        result.put("msg", msg);
	        return result; 
	    }  
	    result.put("result", "success");
	    return result;  
	}  
}
