1.集成 Jedis 
2.添加 Fastjson 



---------------------------------------------
1.集成 Jedis
1.1:
添加依赖
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>

----------
1.2:
配置 Redis
#redis
redis.host=127.0.0.1
redis.port=6379
redis.timeout=3
redis.poolMaxTotal=10
redis.poolMaxIdle=50
redis.poolMinIdle=10
redis.poolMaxWait=3
#redis

-----------
1.3:
代码示例:
RedisConfig.java
负责读取配置文件的类，变量名需要和配置文件1致
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//此注解将配置文件中，以redis开关的配置信息读取到
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;
    private int poolMaxTotal;
    private int poolMinIdle;
    private int poolMaxIdle;
    private int poolMaxWait;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPoolMaxTotal() {
        return poolMaxTotal;
    }

    public void setPoolMaxTotal(int poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }

    public int getPoolMaxWait() {
        return poolMaxWait;
    }

    public void setPoolMaxWait(int poolMaxWait) {
        this.poolMaxWait = poolMaxWait;
    }

    public int getPoolMinIdle() {
        return poolMinIdle;
    }

    public void setPoolMinIdle(int poolMinIdle) {
        this.poolMinIdle = poolMinIdle;
    }

    public int getPoolMaxIdle() {
        return poolMaxIdle;
    }

    public void setPoolMaxIdle(int poolMaxIdle) {
        this.poolMaxIdle = poolMaxIdle;
    }
}



RedisService.java
负责管理 Redis
package com.imooc.miaosha.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 设置对象
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length()<=0){
                return false;
            }
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if(seconds <= 0){
                jedis.set(realKey,str);
            }else{
                jedis.setex(realKey,seconds,str);
            }
            return true;
        }finally{
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if(value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == String.class){
            return (String)value;
        }
        if(clazz == int.class || clazz == Integer.class){
            return value+"";
        }
        if(clazz == long.class || clazz == Long.class){
            return value+"";
        }
        return JSONObject.toJSONString(value);
    }

    /**
     * 获取单个对象
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String readKey = prefix.getPrefix() + key;
            String str = jedis.get(readKey);
            T t = stringToBean(str,clazz);
            return t;
        }finally{
            returnToPool(jedis);
        }
    }

    private <T> T stringToBean(String str,Class<T> clazz) {
        if(str == null){
            return null;
        }
        if(clazz == String.class){
            return (T) str;
        }
        if(clazz == int.class || clazz == Integer.class){
            return (T) Integer.valueOf(str);
        }
        if(clazz == long.class || clazz == Long.class){
            return (T) Long.valueOf(str);
        }
        return JSON.toJavaObject(JSON.parseObject(str),clazz);
    }

    public void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

    /**
     * 判断是否已经存在Key
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public<T> boolean exists(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String readKey = prefix.getPrefix() + key;
            return jedis.exists(readKey);
        }finally{
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public<T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String readKey = prefix.getPrefix() + key;
            return jedis.incr(readKey);
        }finally{
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public<T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String readKey = prefix.getPrefix() + key;
            return jedis.decr(readKey);
        }finally{
            returnToPool(jedis);
        }
    }

}



RedisPoolFactory.java
Redis 连接池工厂，负责连接 Redis
package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;
    //注入Spring容器中
    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(redisConfig.getPoolMinIdle());
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);

        JedisPool jedisPool = new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout()*1000);
        return jedisPool;
    }
}


Redis 相关处理类

KeyPrefix.java
public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}

BasePrefix.java
public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix){
        this(0,prefix);
    }

    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    //默认0代表永不过期
    public int expireSeconds(){
        return 0;
    }
    public String getPrefix(){
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}

业务类 Key
UserKey.java
public class UserKey extends BasePrefix{

    public UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}






-------------------------
2.添加 Fastjson
2.1
添加依赖
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.47</version>
</dependency>




