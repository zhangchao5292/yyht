package com.yyht.dao;

import com.yyht.entity.Admin;


public interface LoginMapper {

	Admin findByUsername(String username);
	
	Admin findByRealUsername(String username);

	Admin checkLogin(Admin admin);
}
