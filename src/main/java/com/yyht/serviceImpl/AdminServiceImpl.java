package com.yyht.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yyht.common.page.DataTables;
import com.yyht.dao.AdminMapper;
import com.yyht.entity.Admin;
import com.yyht.service.IAdminService;
@Service("adminService")
public class AdminServiceImpl implements IAdminService{
	
	private static final Logger logger=LoggerFactory.getLogger(AdminServiceImpl.class);
	@Resource
	private AdminMapper adminMapper;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addAdmin(Admin admin) {
		adminMapper.saveAdmin(admin);
	}
	
	@Override
	public Admin findAdminById(int id) {
		logger.info("===========》request params agentId:{}",id);
		return adminMapper.findAdminById(id);
	}

	@Override
	public DataTables findAdminList(DataTables dataTables) {
		Map<String, Object> params=new HashMap<String, Object>();//传给Mapper的参数
		params.put("sSearch", dataTables.getSSearch());
		params.put("iDisplayStart", Integer.parseInt(dataTables.getiDisplayStart()));
		params.put("pageDisplayLength", Integer.parseInt(dataTables.getPageDisplayLength()));
		List<Map<String, Object>> list = adminMapper.findAdminList(params);
		dataTables.setAaData(list);
		dataTables.setiTotalDisplayRecords(adminMapper.queryAdminCount(params));
		dataTables.setiTotalRecords(adminMapper.queryAdminTotal());
		return dataTables;
	}
	public static void main(String[] args) {
		for(int i=0;i<10000;i++){
		logger.info("我是logger1，info");
		logger.error("我是logger1，error");
		}
	}
}
