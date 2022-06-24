package com.example.seckillcontroller.interceptor;//package com.example.springbootrabbitmq.aop;

import com.example.seckillcontroller.annotation.MyAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoginInterceptor2 {

    @Pointcut("@annotation(myAnnotation)")
    public void addAdvice(MyAnnotation myAnnotation) {
    }

    @Around(value = "addAdvice(myAnnotation)",argNames = ("joinPoint,myAnnotation"))
//    @Around("@annotation(com.example.springbootrabbitmq.annotation.MyAnnotation)")
    public Object Interceptor(ProceedingJoinPoint joinPoint,MyAnnotation myAnnotation) {
        System.out.println("====Interceptor====");
        Object result = null;
        Object[] args = joinPoint.getArgs();

        if (args != null && args.length > 0) {
            //对参数进行校验
            System.out.println("方法参数1   " + args[0]);
            System.out.println("方法参数2   " + args[1]);
        }
        //处理逻辑
        if (true) {
            try {
                result = joinPoint.proceed();
                System.out.println("方法原始返回值  " + result);
                return result;
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return "ok";
        } else {
            return "error";
        }
    }

    @Before(value = "addAdvice(myAnnotation)" )
    public void before(JoinPoint joinPoint,MyAnnotation myAnnotation) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        //获取注解上的参数
        System.out.println("打印：" + annotation.value() + " 开始前");
    }

    @After(value = "addAdvice(myAnnotation)" )
    public void after(MyAnnotation myAnnotation) {
        System.out.println("after方法执行后");
    }

}