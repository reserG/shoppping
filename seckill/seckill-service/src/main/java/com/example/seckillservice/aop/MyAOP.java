package com.example.seckillservice.aop;//package com.example.springbootrabbitmq.aop;
//
//import com.example.springbootrabbitmq.annotation.MyAnnotation;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Aspect
//@Component
//public class MyAOP {
//
//    @Pointcut("@annotation(com.example.springbootrabbitmq.annotation.MyAnnotation)")
//    public void addAdvice() {
//    }
//
//    @Around("addAdvice()")
////    @Around("@annotation(com.example.springbootrabbitmq.annotation.MyAnnotation)")
//    public Object Interceptor(ProceedingJoinPoint joinPoint) {
//        System.out.println("====Interceptor====");
//        Object result = null;
//        Object[] args = joinPoint.getArgs();
//        System.out.println("方法参数1   " + args[0]);
//        System.out.println("方法参数2   " + args[1]);
//        if (args != null && args.length > 0) {
//            //对参数进行校验
//        }
//        //处理逻辑
//        if (true) {
//            try {
//                result = joinPoint.proceed();
//                System.out.println("方法原始返回值  " + result);
//                return result;
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//            return "ok";
//        } else {
//            return "error";
//        }
//    }
//
//    @Before("addAdvice()")
//    public void before(JoinPoint joinPoint) {
//        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
//        Method method = sign.getMethod();
//        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
//        //获取注解上的参数
//        System.out.println("打印：" + annotation.value() + " 开始前");
//    }
//
//    @After("addAdvice()")
//    public void after() {
//        System.out.println("after方法执行后");
//    }
//
//}