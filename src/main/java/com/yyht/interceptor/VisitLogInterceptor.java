package com.yyht.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yyht.common.utils.Constant;
import com.yyht.common.utils.RemoteRequest;
import com.yyht.entity.Admin;


/**
 * 接口访问日志
 * @author lonyee
 *
 */
public class VisitLogInterceptor extends HandlerInterceptorAdapter{
	private Log logger  = LogFactory.getLog(VisitLogInterceptor.class);
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        Object user = request.getSession().getAttribute(Constant.SESSION_USER);
        Integer userId = -1;
        String account = "";
        if (user!=null) {
        	Admin admin = (Admin) user;
        	userId = admin.getId();
        	account = admin.getAccount();
        }
        StringBuilder strBuilder = new StringBuilder(30);
        strBuilder.append("{");
        Enumeration<String> enu=request.getParameterNames();  
        while(enu.hasMoreElements()){  
        	String paraName = (String)enu.nextElement();
        	strBuilder.append(paraName);
        	strBuilder.append(": ");
        	//屏蔽密码显示
        	if ("password".equals(paraName) || "oldPsw".equals(paraName) || "newPsw".equals(paraName)) {
            	strBuilder.append("******");
            	strBuilder.append(", ");
            	continue;
        	} 
        	String value = request.getParameter(paraName).replaceAll("%", "%25");
        	try {
        		value = URLDecoder.decode(value, "utf-8");
        	}catch (UnsupportedEncodingException e) { }
        	
        	strBuilder.append(value);
        	strBuilder.append(", "); 
        }
        if (strBuilder.length()>=2) {
        	strBuilder = strBuilder.deleteCharAt(strBuilder.length()-2);
        }
        strBuilder.append("}");
        String logs = String.format("[clientIp: %s, userId: %s, account: %s, referer: %s, page: %s, params: %s]", RemoteRequest.getRequestIP(request), userId, account, request.getHeader("referer"), request.getRequestURL(), strBuilder.toString());
        logger.info(logs);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
