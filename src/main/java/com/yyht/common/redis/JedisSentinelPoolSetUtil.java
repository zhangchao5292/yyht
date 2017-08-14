package com.yyht.common.redis;

import java.util.Random;
import java.util.Set;



import com.yyht.common.redis.factory.JedisPoolFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class JedisSentinelPoolSetUtil extends JedisSentinelPoolUtil{

	public static final Random r = new Random();

	public static void setSadd(String key,String []members){
		
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.sadd(key, members);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
	
	public static  Set<String> getSmembers(String key){
		Set<String> set = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			set = jedis.smembers(key);
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
	
	public static  long getScard(String key){
		Jedis jedis = getJedis();
		long flag = jedis.scard(key);
		jedisClose(jedis);
		return flag;
	}
	public static  Set<String> getSdiff(String []keys){
		Jedis jedis = getJedis();
		Set<String>  set= jedis.sdiff(keys);
		jedisClose(jedis);
		return set;
	}
	public static  Set<String> getSinter(String []keys){
		Jedis jedis = getJedis();
		Set<String>  set= jedis.sinter(keys);
		jedisClose(jedis);
		return set;
	}
	public static  Set<String> getSunion(String []keys){
		Jedis jedis = getJedis();
		Set<String>  set= jedis.sunion(keys);
		jedisClose(jedis);
		return set;
	}
	public static ScanResult<String> sscan(String key,String cursor,ScanParams scanParams){
		Jedis jedis = getJedis();
		ScanResult<String>  str= jedis.sscan(key, cursor, scanParams);
		jedisClose(jedis);
		return str;
	}
	public static  void srem(String key ,String []members){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool =JedisPoolFactory.getPool();
			jedis =pool.getResource();
			jedis.srem(key, members);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
        	if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
	}
//	public static void main(String[] args) {
//		String key = "COM_PARTY_PROD";
//		Set<String> ss = getSmembers(key);
////		
//		System.out.println(ss.size());
////		for(String asdf :ss){
////			System.out.println(asdf);
////		}
////		String members[] = new String[2000];
////		for(int i=0;i<members.length;i++){
////			members[i] = "ysd22_"+i;
////		}
////		setSadd(key,members);
////		System.out.println(11);
//		ScanParams a = new ScanParams();
//		a.match("ysd*");
//		ScanResult<String> x = getJedis().sscan(key, "0", a);
//		String asd = x.getStringCursor();
//		System.out.println(x.getResult().size());
////		System.out.println(getSmembers(key).size());
//	}
	
	
	public static void main(String[] args) {
//		System.out.println(JedisSentinelPoolSetUtil.getSmembers("ysd1").size());
	}
}
