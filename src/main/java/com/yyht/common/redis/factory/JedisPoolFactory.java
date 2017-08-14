package com.yyht.common.redis.factory;


import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolFactory  {
    protected Logger log = LoggerFactory.getLogger(getClass());
    private static String ip ;
    private static int port;
    private static int retryNum;
    private static int timeOut ;
    private static String passwd ;
    private static JedisPoolConfig config; 
    public static JedisPoolFactory jedisPoolFactory;
    
	/**
	 * 私有构造器.
	 */
	private JedisPoolFactory() {
	    
	}
	/**
	 * 私有构造器.
	 */
	private JedisPoolFactory(String ip ,int port,int retryNum , int timeOut,JedisPoolConfig config,String passwd) {
	    JedisPoolFactory.ip = ip;
	    JedisPoolFactory.port = port;
	    JedisPoolFactory.timeOut = timeOut;
	    JedisPoolFactory.retryNum =retryNum;
	    JedisPoolFactory.config = config;
	    JedisPoolFactory.passwd = passwd;
	}
	private static Map<String,JedisPool> maps  = new HashMap<String,JedisPool>();
	
	public static JedisPoolFactory getInstance(
			String ip,int port,int retryNum , int timeOut,JedisPoolConfig config,String passwd) {
		if (jedisPoolFactory == null) {
			jedisPoolFactory = new JedisPoolFactory(ip,
					port,retryNum,timeOut,config,passwd );
		}
		return jedisPoolFactory;
	}
    /**
     * 获取连接池.
     * @return 连接池实例111
     */
    public static JedisPool getPool() {
    	String key = ip+":" +port;
    	JedisPool pool = null;
        if(!maps.containsKey(key)) {
            try{  
                /**
                 *如果你遇到 java.net.SocketTimeoutException: Read timed out exception的异常信息
                 *请尝试在构造JedisPool的时候设置自己的超时值. JedisPool默认的超时时间是2秒(单位毫秒)
                 */
            	
                pool = new JedisPool(config, ip, port,timeOut,passwd);
                maps.put(key, pool);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }else{
        	pool = maps.get(key);
        }
        return pool;
    }

    /**
     *类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     *没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class RedisUtilHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static JedisPoolFactory instance = new JedisPoolFactory();
    }

    /**
     *当getInstance方法第一次被调用的时候，它第一次读取
     *RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静
     *态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
     *这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
     */
	public static JedisPoolFactory getInstance() {
		return RedisUtilHolder.instance;
	}
	
	/**
	 * 获取Redis实例.
	 * @return Redis工具类实例
	 */
	public Jedis getJedis() {
		Jedis jedis  = null;
		int count =0;
		do{
    		try{ 
    			jedis = getPool().getResource();
    			//log.info("get redis master1!");
    		} catch (Exception e) {
    			log.error("get redis master1 failed!", e);
    			 // 销毁对象  
    			getPool().returnBrokenResource(jedis);  
    		}
    		count++;
		}while(jedis==null&&count<retryNum);
		return jedis;
	}

	/**
	 * 释放redis实例到连接池.
     * @param jedis redis实例
     */
	public synchronized void  closeJedis(Jedis jedis) {
		if(jedis != null) {
		    getPool().returnResource(jedis);
		}
	}
}

