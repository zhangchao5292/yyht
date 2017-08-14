package com.yyht.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.yyht.common.response.ResultResponse;


public class BaseController {
	 @Autowired
	 public HttpServletRequest request;
	 @Autowired
	 public HttpServletResponse response; 
	 
	 ResultResponse resultResponse=new ResultResponse();
	 public void push2client(Object obj) throws Exception{
		 	Gson gson = new Gson();

			String jsonString = gson.toJson(obj);  
			System.out.println(jsonString);
			PrintWriter writer = response.getWriter();
			writer.write(jsonString);
			writer.flush();
		}
}
