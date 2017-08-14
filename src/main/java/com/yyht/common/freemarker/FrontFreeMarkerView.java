package com.yyht.common.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class FrontFreeMarkerView  extends FreeMarkerView{
	 public static final String CONTEXT_PATH = "base";
     @Override
     protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        model.put(CONTEXT_PATH, request.getContextPath());
        
        HttpSession session = request.getSession(false);
        if (session != null) {
//        	Users user = (Users) session.getAttribute("user");
//        	if(user!=null)
//            model.put("username", user.getName());
        }
        
     }

}
