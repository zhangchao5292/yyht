package com.yyht.common.redis;

import java.util.HashSet;
import java.util.Set;




import com.yyht.common.redis.factory.JedisPoolFactory;
import com.yyht.common.redis.factory.JedisSentinelPoolFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class JedisSentinelPoolUtil {

    public static Set<String>              masterSlaveSet           = new HashSet<String>();
    public static String                   useRedis;
    public static String                   isUseRedis;
    public static String                   isUpdateRedis;
    public static JedisSentinelPoolFactory jedisSentinelPoolFactory = null;
    public static String                   contextRedisFlag;
    public static JedisPoolFactory         jedisPoolFactory;
    static {
        contextRedisFlag = GetRedisContext.getPropByCodeName("CONTEXT_REDIS_FLAG");
        JedisPoolConfig config = new JedisPoolConfig();

        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(Boolean.valueOf(GetRedisContext
            .getPropByCodeName("BLOCK_WHEN_EXHAUSTED")));
        // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        // 是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);
        // MBean ObjectName = new
        // ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" +
        // "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
        config.setJmxNamePrefix("pool");
        // 是否启用后进先出, 默认true
        config.setLifo(true);
        // 最大空闲连接数, 默认8个
        config.setMaxIdle(Integer.valueOf(GetRedisContext.getPropByCodeName("REDIS_MAX_IDLE")));
        // 最大连接数, 默认8个
        config.setMaxTotal(Integer.valueOf(GetRedisContext.getPropByCodeName("REDIS_MAX_TOTAL")));
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,
        // 默认-1
        config.setMaxWaitMillis(Long.valueOf(GetRedisContext
            .getPropByCodeName("REDIS_MAX_WAITMILLIS")));
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(1800000);
        // 最小空闲连接数, 默认0
        config.setMinIdle(Integer.valueOf(GetRedisContext.getPropByCodeName("REDIS_MIN_IDLE")));
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);
        // 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数
        // 时直接逐出,不再根据MinEvictableIdleTimeMillis判断 (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(1800000);
        // 在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);
        // 在空闲时检查有效性, 默认false
        config.setTestWhileIdle(false);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        config.setTimeBetweenEvictionRunsMillis(-1);

        String timeOut = GetRedisContext.getPropByCodeName("CONTEXT_REDIS_TIME_OUT");
        String ip = GetRedisContext.getPropByCodeName("CONTEXT_REDIS_IP");
        String port = GetRedisContext.getPropByCodeName("CONTEXT_REDIS_PORT");
        String retryNum = GetRedisContext.getPropByCodeName("CONTEXT_REDIS_RETRY_NUM");
        String passwd = GetRedisContext.getPropByCodeName("CONTEXT_REDIS_PASSWD");

            jedisPoolFactory = JedisPoolFactory.getInstance(ip, Integer.valueOf(port),
                Integer.valueOf(retryNum), Integer.valueOf(timeOut), config,passwd);
    }

    protected static Jedis getJedis() {
        if (!"POOL".equals(contextRedisFlag)) {
            return JedisSentinelPoolFactory.getJedis();
        } else {
            return jedisPoolFactory.getJedis();
        }
    }

    public static void jedisClose(Jedis jedis) {
        if (!"POOL".equals(contextRedisFlag)) {
            JedisSentinelPoolFactory.jedisClose(jedis);
        } else {
            jedisPoolFactory.closeJedis(jedis);
        }
    }

    public static void deleteByKey(String[] keys) {
        Jedis jedis = getJedis();
        jedis.del(keys);
        jedisClose(jedis);
    }

    public static void deleteByKey(String key) {
        Jedis jedis = getJedis();
        jedis.del(key);
        jedisClose(jedis);
    }

    public static void main(String[] args) {
        // Map<String,Object> b = new HashMap<String,Object>();
        // b.put("ysd", "1");
        // b.put("why", "2");
        // b.put("wgt", "3");
        // String x[]= {"ysd","why","name"};
        //
        // deleteHashByKeyAndField("blue" , x);
        // Map<String,String> a = getHashMultiAllByKey("PPRICE_EXP");
        // System.out.println(a.size());
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            System.out.println(JedisSentinelPoolSetUtil.getSmembers("ysd").size());

        }
    }
}
