package com.yyht.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.yyht.common.utils.Constant;
import com.yyht.common.utils.CookiesUtil;
import com.yyht.service.ILoginService;
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Resource
	private ILoginService loginService;
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(){
		ModelAndView mv = new ModelAndView("login/login");
		String account=CookiesUtil.getCookieValue(request, Constant.COOKIE_ACCOUNT);
		mv.addObject("username", account);
		return mv;		
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
//	@RequestMapping("/login")
//	@ResponseBody
//	public Boolean login(@RequestParam(value="username", required=true) String username,
//			@RequestParam(value="password", required=true) String password) {
//		if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
//			throw new I18nMessageException(1005, "用户名或密码不能为空");
//		} 
//		Boolean result=loginService.checkLogin(username,password);
//		return result;
//	}

}
