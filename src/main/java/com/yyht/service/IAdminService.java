package com.yyht.service;

import com.yyht.common.page.DataTables;
import com.yyht.entity.Admin;

public interface IAdminService {
	/**
	 * 添加用户
	 * @param admin
	 */
	public void addAdmin(Admin admin );

	public Admin findAdminById(int i);

	public DataTables findAdminList(DataTables datables);

}
