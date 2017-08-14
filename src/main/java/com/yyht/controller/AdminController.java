package com.yyht.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yyht.common.page.DataTables;
import com.yyht.common.response.ResultMessage;
import com.yyht.common.response.ResultResponse;
import com.yyht.common.response.ServiceException;
import com.yyht.entity.Admin;
import com.yyht.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	
	@Resource 
	private IAdminService adminService;
	/**
	 * 添加示例
	 * @return
	 */
	@RequestMapping(value = "/addAdmin")
	@ResponseBody
	public ResultResponse addAdmin() {
		adminService.addAdmin(new Admin("zhangsan", "123456"));
		return resultResponse.success();
	}
	
	/**
	 * 查询示例
	 * @return
	 */
	@RequestMapping(value = "/getAdmin")
	@ResponseBody
	public ResultResponse getAdmin(@RequestParam Integer id) {
		Admin admin=adminService.findAdminById(id);
		return resultResponse.success(admin);
	}
	
	/**
	 * 分页示例
	 * @return
	 */
	@RequestMapping(value = "/adminList")
	@ResponseBody
	public DataTables adminList() {
		DataTables dataTables=DataTables.createDataTables(request);
		DataTables list=adminService.findAdminList(dataTables);
		return list;
	}
	
	/**
	 * 异常处理示例1
	 * @return
	 */
//	@RequestMapping("/exception")
//	@ResponseBody
//	public  ResultResponse exception(){
//		try {
//			int i=1/0;
//		} catch (Exception e) {
//			throw new I18nMessageException(10006, "请求失败!");
//		}
//		return resultResponse.success();		
//	}
	/**
	 * 异常处理示例2
	 * @return
	 * @throws ServiceException 
	 */
	@RequestMapping("/exception2")
	@ResponseBody
	public  ResultResponse exception2() {
		try {
			if (true) {
				throw new ServiceException(ResultMessage.F4007);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return resultResponse.success();		
	}
	
}
