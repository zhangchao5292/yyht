package com.yyht.common.redis;

import java.util.Random;
import java.util.Set;



import com.yyht.common.redis.factory.JedisPoolFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSentinelPoolZSetUtil extends JedisSentinelPoolUtil{
	

	public static final Random r = new Random();

	/**
	 * 有序集合的数据缓存，需要传入排序权重
	 * @param key
	 * @param value
	 * @param sortNum
	 */
	public static void setZadd(String key,String value,double sortNum){
		
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.zadd(key, sortNum, value);
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
	 * 获取有序集合
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static  Set<String> getZmembers(String key,long start,long end){
		Set<String> set = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			//set = jedis.smembers(key);
			set = jedis.zrange(key, start, end);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return set;
	}
	public static  void zrem(String key ,String []members){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.zrem(key, members);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	
	
	public static void main(String[] args) {
//		System.out.println(JedisSentinelPoolSetUtil.getSmembers("ysd1").size());
	}
}
