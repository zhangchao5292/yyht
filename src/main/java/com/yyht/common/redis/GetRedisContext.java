package com.yyht.common.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetRedisContext {
	private static Logger logger = LoggerFactory
			.getLogger(GetRedisContext.class);
	//IP 地址
	public static String CONTEXT_REDIS_IP = "127.0.0.1";
	//端口
	public static String CONTEXT_REDIS_PORT="6379";
	public static String CONTEXT_POOL_FLAG = "pool";
	//重试链接数
	public static String CONTEXT_REDIS_RETRY_NUM="10";
	//链接超时时间  毫秒
	public static String CONTEXT_REDIS_TIME_OUT="1000";
	//最大能够保持idel状态的对象数 
	public static String REDIS_MAX_IDLE="8";
	//redis链接需要的认证密码 
	public static String CONTEXT_REDIS_PASSWD="";

	static Properties props = null;
	static {
		// 设置代理服务器-可以解析properties文件
		
		InputStream in = null;
		try {
			props = new Properties();
			in = GetRedisContext.class.getClassLoader().getResourceAsStream(
					"redis/redis_config.properties");
			props.load(in);
			CONTEXT_REDIS_IP = props.getProperty("CONTEXT_REDIS_IP");
			CONTEXT_POOL_FLAG =props.getProperty("CONTEXT_POOL_FLAG");
			CONTEXT_REDIS_PORT =  props.getProperty("CONTEXT_REDIS_PORT");
			CONTEXT_REDIS_RETRY_NUM = props.getProperty("CONTEXT_REDIS_RETRY_NUM");
			CONTEXT_REDIS_TIME_OUT = props.getProperty("CONTEXT_REDIS_TIME_OUT");
			REDIS_MAX_IDLE = props.getProperty("REDIS_MAX_IDLE");
			CONTEXT_REDIS_PASSWD=props.getProperty("CONTEXT_REDIS_PASSWD");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("读取redis 配置文件抛异常："+e.toString());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("i/o关闭抛异常："+e.toString());
				}
			}
		}
	}
	public static String getPropByCodeName(String code){
		return  props.getProperty(code);
	}
}
