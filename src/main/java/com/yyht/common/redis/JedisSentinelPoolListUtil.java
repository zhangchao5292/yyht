package com.yyht.common.redis;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.yyht.common.redis.factory.JedisPoolFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSentinelPoolListUtil extends JedisSentinelPoolUtil {

	public static final Random r = new Random();

	/**
	 * 从左侧压入数据
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public static Long lpush(String key, String... values) {
		Set<String> set = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			return jedis.lpush(key, values);
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
	 * 从右侧压入数据
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public static Long rpush(String key, String... values) {
		Set<String> set = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			return jedis.rpush(key, values);
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
	 * 从右侧弹出数据
	 * 
	 * @param key
	 * @return
	 */
	public static String rpop(String key) {
		Set<String> set = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			return jedis.rpop(key);
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
	 * 从左侧弹出数据
	 * 
	 * @param key
	 * @return
	 */
	public static String lpop(String key) {
		Set<String> set = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			return jedis.lpop(key);
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
	 * 分段处理
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> range(String key, int start, int end) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			return jedis.lrange(key, start, end);
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
	 * 分段处理
	 * 
	 * @param 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean flushDB() {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			 jedis.flushDB();
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
	 * 分段处理
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean rmve(String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			return jedis.del(key)>0?true:false;
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
	public static Long length(String key) {
		Set<String> set = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = JedisPoolFactory.getPool();
			jedis = pool.getResource();
			return jedis.llen(key);
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
	
	/*public static void main(String[] args) {
		String[] seqs = new String[1000];
		// for (int i = 0; i < 1000; i++) {
		// seqs[i] = "20151111" + i;
		// }
		//
		// lpush("sequence", seqs);

		while (true) {
			System.out.println("=====================");
			for (int i = 0; i < 50; i++) {
				System.out.println(rpop("sequence"));
			}

			if (length("sequence") < 500) {
				System.out.println("sequence小于500，压入100个数字");
				String[] arr = new String[100];
				for (int i = 0; i < 100; i++) {
					UUID uuid = UUID.randomUUID();
					arr[i] = MD5Util.getSign(uuid.toString());
				}

				lpush("sequence", arr);
			}

			System.out.println("=====================");
		}
	}*/
}
