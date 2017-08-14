package com.yyht.common.redis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yyht.common.redis.factory.JedisPoolFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSentinelPoolHashUtil extends JedisSentinelPoolUtil{

	/**
	 * 单个hashset写入
	 * @param key
	 * @param field
	 * @param value
	 */
	public static void setHashSet(String key,String field,String value){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.hset(key, field, value);
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
	 * 单个hashsetq读取
	 * @param key
	 * @param field
	 * @param value
	 */
	public static String getHashSet(String key,String field){
		String str = "";
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			str = jedis.hget(key, field);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return str;
	}
	/**
	 * 单个hashset写入(当不存在field时候写入)
	 * @param key
	 * @param field
	 * @param value
	 */
	public static void setHashSetNx(String key,String field,String value){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.hsetnx(key, field, value);
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
	 * 批量值写入-散列
	 * @param key
	 * @param map<field,value>
	 */
	public static void setHashMultiSet(String key,Map map){
		
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.hmset(key, map);
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
	 * 批量值写入-有序集合
	 * @param key
	 * @param map<field,value>
	 */
	public static void setHashMultiZset(String key,Map map){
		
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.zadd(key, map);
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
	 * 通过key获取hash中所有值
	 * @param key
	 * @return
	 */
	public static List<String> getHashMultiValuesByKey(String key){
		List<String> listStr = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			listStr = jedis.hvals(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return listStr;
	}
	/**
	 * 通过key获取hash中所有字段
	 * @param key
	 * @return
	 */
	public static Set<String> getHashMultiKeysByKey(String key){
		Set<String> setStr = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			setStr = jedis.hkeys(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return setStr;
	}
	/**
	 * 通过key获取hash中field和value
	 * @param key
	 * @return
	 */
	public static Map<String,String> getHashMultiAllByKey(String key){
		
		Map<String,String>  mapStr = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			mapStr = jedis.hgetAll(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return mapStr;
	}
	/**
	 * 通过key和fields数组获取其对应的value
	 * @param key
	 * @param fields
	 * @return
	 */
	public static List<String> getHashMultiValuesByKeyAndFields(String key,String []fields){
		List<String>  listStr = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			listStr = jedis.hmget(key, fields);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return listStr;
	}
	/**
	 * 通过key获取hash中的数量
	 * @param key
	 * @return
	 */
	public static long getHashMutilCountByKey(String key){
		long lo = 0l;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			lo = jedis.hlen(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return lo;
	}
	public static boolean getHExistByKeyAndField(String key,String field){
		boolean bo = true;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			bo = jedis.hexists(key,field);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return bo;
	}
	public static  long deleteHashByKeyAndField(String key,String fields[]){
		long lo = 0l;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			lo = jedis.hdel(key, fields);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return lo;
	}
	
	public static  Set<String> getKeys(String pattern, String type){
		Set<String> retSet = new HashSet<String>();
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			Set<String> set = jedis.keys(pattern);
			
			if(set != null){
				Iterator<String>iterator=set.iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					if(jedis.type(key).equals(type)){
						retSet.add(key);
					}
				}
			}
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
		return retSet;
	}
	/**
	 * 判断key是否存在
	 * @param key
	 * @return boolean
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
}
