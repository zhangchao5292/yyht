package com.yyht.common.redis.factory;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelPoolFactory {
	public static JedisSentinelPoolFactory sentinelPoolFactory;
	public static JedisSentinelPool sentinelPool ;

	public JedisSentinelPoolFactory(){
		super();
	}
	public JedisSentinelPoolFactory(Set<String> masterSlaveSet, String useRedis,JedisPoolConfig config,int timeOut) {
		sentinelPool = new JedisSentinelPool(useRedis,
				masterSlaveSet,config,timeOut);
	}

	public static JedisSentinelPoolFactory getInstance(
			Set<String> masterSlaveSet, String useRedis,JedisPoolConfig config,int timeOut) {
		if (sentinelPoolFactory == null) {
			sentinelPoolFactory = new JedisSentinelPoolFactory(masterSlaveSet,
					useRedis,config,timeOut);
		}
		return sentinelPoolFactory;
	}
	public static Jedis getJedis(){
		Jedis jedis = sentinelPool.getResource();
		return jedis;
	}
	public static void jedisClose(Jedis jedis){
		sentinelPool.returnResource(jedis);
	}
}
