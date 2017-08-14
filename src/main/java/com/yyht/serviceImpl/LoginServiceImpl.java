package com.yyht.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yyht.dao.LoginMapper;
import com.yyht.entity.Admin;
import com.yyht.service.ILoginService;
@Service("loginService")
public class LoginServiceImpl implements ILoginService {
	
	@Resource
	private LoginMapper loginMapper;
	public Boolean checkLogin(String username, String password) {
		Admin admin=new Admin();
		admin.setName(username);
		admin.setPassword(password);
		Admin adminResult=loginMapper.checkLogin(admin);
		if (null == adminResult) {
			return false;
		} else {
			return true;
		}
	}

}
