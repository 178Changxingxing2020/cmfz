package com.baizhi.cxx.aspect;

import com.baizhi.cxx.annotation.LogAnnotation;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Aspect
@Configuration
public class LogAspect {

    @Autowired
    private  HttpServletRequest request;

//    @Around("execution(* com.baizhi.cxx.serviceImpl.*.*(..))")
//    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
//
//        HttpSession session = request.getSession();
//        Admin admin = (Admin) session.getAttribute("admin");
//
//        Date date = new Date();
//        //获取方法名
//        String name = proceedingJoinPoint.getSignature().getName();
//        String status;
//        try {
//            Object proceed = proceedingJoinPoint.proceed();
//            status="success";
//            return proceed;
//        } catch (Throwable throwable) {
//            status="error";
//            throwable.printStackTrace();
//            return  null;
//        }
//    }

 @Around("@annotation(com.baizhi.cxx.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){

        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("admin");
        String name=null;
        if(admin!=null){
            name=admin.getUsername();
        }

        Date date = new Date();
        //获取方法名
        //String name = proceedingJoinPoint.getSignature().getName();
        //获取注解信息
     MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
     LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
     String value = annotation.value();

     String status=null;
        try {
            Object proceed = proceedingJoinPoint.proceed();
            status="success";
            Log log = new Log(null,name, date, value, status);
            System.out.println(log);
            return proceed;
        } catch (Throwable throwable) {
            status="error";
            Log log = new Log(null, name, date, value, status);
            System.out.println(log);
            throwable.printStackTrace();
            return  null;
        }



 }


}
