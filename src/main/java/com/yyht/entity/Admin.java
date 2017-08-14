package com.yyht.entity;



public class Admin extends BasicEntityBean{
	
	public String name;
	public String account;
	public String password;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public Admin() {
	}
	
	

}
