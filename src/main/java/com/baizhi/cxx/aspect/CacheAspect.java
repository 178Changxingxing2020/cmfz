package com.baizhi.cxx.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CacheAspect {
    @Autowired
    RedisTemplate redisTemplate;

    @Around("@annotation(com.baizhi.cxx.annotation.AddOrSelectCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){
        String clazz = proceedingJoinPoint.getTarget().getClass().toString();
        System.out.println(clazz);
        String name = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        String key=name;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            key += arg;
        }

        Object o = redisTemplate.opsForHash().get(clazz, key);
        if(o!=null){
            return o;
        }
        try {
            Object proceed = proceedingJoinPoint.proceed();
            redisTemplate.opsForHash().put(clazz,key,proceed);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

    }



    @Around("@annotation(com.baizhi.cxx.annotation.ClearCache)")
    public Object ClearCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String s = proceedingJoinPoint.getTarget().getClass().toString();
        redisTemplate.delete(s);
        return proceedingJoinPoint.proceed();
    }
}

