package com.yyht.common.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.yyht.common.redis.factory.JedisPoolFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

public class JedisSentinelStringUtil extends JedisSentinelPoolUtil {
	private static Logger logger = LoggerFactory.getLogger(JedisSentinelStringUtil.class);
	
	/**
	 * 单个string写入,如果key存在，则覆盖
	 * @param key
	 * @param value
	 */
	public static void setString(String key,String value){
		JedisPool pool = null;
		Jedis jedis = null;
		try { 
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.set(key, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	/**
	 * 单个string写入,如果key存在，则覆盖;且设置key有效期
	 * @param key
	 * @param value
	 * @param expireTime---秒
	 */
	public static boolean setStringWithEx(String key,String value,int expireTime){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean flag=true;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			//jedis.set(key, value);
			jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            flag=false;
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return flag;
	}
	
	/**
	 * 单个string获取,如果不存在,则返回null
	 * @param key
	 */
	public static String getString(String key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			return jedis.get(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return null;
	}
	
	
	
	/**
	 * 单个string写入,如果key存在，则覆盖;且设置key有效期
	 * @param key
	 * @param value
	 * @param expireTime---秒
	 */
	public static boolean setWithEx(byte[] key, byte[] value,int expireTime){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean flag=true;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            //byte[] key 为未知序列化方式，不能打印出key值
            logger.error("set redis key error.", e);
            flag=false;
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return flag;
	}
	
	/**
	 * 单个string获取,如果不存在,则返回null
	 * @param key
	 */
	public static byte[] get(byte[] key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			return jedis.get(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            //byte[] key 为未知序列化方式，不能打印出key值
            logger.error("get redis key error.", e);
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return null;
	}
	
	/**
	 * 获取所有指定keys
	 * @param key
	 */
	public static Set<byte[]> keys(byte[] key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			return jedis.keys(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            logger.error("get redis keys error.", e);
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return null;
	}
	
	/**
	 * 获取所有指定keys
	 * @param key
	 */
	public static void delete(byte[] key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.del(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	
	
	/**
	 * 判定key是否存在
	 * @param key
	 */
	public static boolean existsKey(String key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			return jedis.exists(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return false;
	}
	/**
	 * 累加
	 * @param key
	 */
	public static void incByKey(String key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.incrBy(key, 1);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	/**
	 * 递减
	 * @param key
	 */
	public static void decByKey(String key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.decrBy(key, 1);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	
	/**
	 * 累加
	 * @param key
	 */
	public static void INCRBYFLOAT(String key,Double value){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.incrByFloat(key, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	
	/**
	 * 删除redis
	 * @param key
	 */
	public static void remove(String key){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.del(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	
	/**
	 * 设置key有效期
	 * @param key
	 * @param seconds---秒
	 */
	public static boolean expireKey(String key,int seconds){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean flag=true;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.expire(key, seconds);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            flag=false;
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		
		return flag;
	}
	
	public static void main(String[] args) {
		//setStringWithEx("15313815224","352453",300);
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println(getString("15313815224"));
		
	}
	
	protected static Jedis getJedis(){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			return jedis;
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return null;
	}
	/** 
    * 删除key，可以是一个，也可以是多个key 
    * @param keys 
    */  
   public synchronized static void deleteKey(String ... keys ){  
       getJedis().del(keys);  
   }  
     
   
   public static Set<String> findAllKeys(String key){
	   
	   return getJedis().keys(key+"*");
   }
   
   /** 
    * 删除前缀为{参数}的所有key<br> 
    * @param prefix 
    */
   public synchronized static int deleteKeyByPrefix(String pattern){  
       //列出所有匹配的key  
       Set<String> keySet = getJedis().keys(pattern+"*");  
       if(keySet == null || keySet.size()<=0){  
           return 0;  
       }  
       String keyArr[] = new String[keySet.size()];  
       int i =0;  
       for (String keys : keySet) {  
           keyArr[i] = keys;  
           i++;  
       }  
       deleteKey(keyArr);  
       return keySet.size();  
   }  
	
}
