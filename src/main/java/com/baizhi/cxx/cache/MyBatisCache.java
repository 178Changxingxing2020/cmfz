package com.baizhi.cxx.cache;

import com.baizhi.cxx.util.MyWebAware;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

public class MyBatisCache implements Cache {

//必须有一个id属性
    private final String id;
//必须有一个id属性的有参构造
    public MyBatisCache(String id){
        this.id=id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getBeanByName("redisTemplate");
        redisTemplate.opsForHash().put(this.id,key.toString(),value);
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getBeanByName("redisTemplate");
        Object o = redisTemplate.opsForHash().get(this.id, key.toString());

        return o;
    }

    @Override
    public Object removeObject(Object o) {
        RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getBeanByName("redisTemplate");
        redisTemplate.delete(this.id);
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
