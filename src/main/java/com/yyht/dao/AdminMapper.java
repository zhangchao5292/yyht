package com.yyht.dao;

import java.util.List;
import java.util.Map;




import com.yyht.entity.Admin;

public interface AdminMapper {

	void saveAdmin(Admin admin);

	Admin findAdminById(int id);

	List<Map<String, Object>> findAdminList(Map<String, Object> params);

	Integer queryAdminCount(Map<String, Object> params);

	Integer queryAdminTotal();

}
