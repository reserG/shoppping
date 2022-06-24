package com.example.seckillcontroller.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.seckillcontroller.annotation.RequireLogin;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.InetAddress;

@Service
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("getRequestURI  === " + request.getRequestURI());

        /**
         * 获取调用 获取主要方法
         */
        System.out.println( " getIP  " +   getIP(request));
        System.out.println(" getRealIp  " +   getRealIp(request));
        if (handler instanceof HandlerMethod) {
            System.out.println("打印拦截方法handler ：{}" + handler);
            System.out.println(((HandlerMethod) handler).getMethodParameters().length);
            System.out.println("方法参数1  " + request.getParameter("msg1"));
            System.out.println("方法参数2  " + request.getParameter("msg2"));
            HandlerMethod hm = (HandlerMethod) handler;
            //处理逻辑如果前置条件不符合,直接重定向到标签页
//			if(条件不符合){
//				response.sendRedirect("/do_login");
//				return super.preHandle(request, response, handler);
//			}
            //获取注解
            RequireLogin accessLimit = hm.getMethodAnnotation(RequireLogin.class);
            if (accessLimit == null) {
                return true;
            }
            //获取注解上的参数
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if (needLogin) {
                System.out.println("MyAnnotationHandler  needlogin");
                //校验是否登录
            }
            //前置条件是否满足
            if (true){
                render(response,"success");
                return true;
            }else {
                render(response,"error");
                return false;
            }
        }
        return true;
    }

    public static String getIP(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        String headerIP = request.getHeader("x-real-ip");
        if(headerIP == null || "".equals(headerIP) || "null".equals(headerIP)){
            headerIP = request.getHeader("x-forwarded-for");
        }
        System.out.println("headerIP:"+headerIP);
        if(headerIP !=null && !"".equals(headerIP) && !"null".equals(headerIP)){
            ip = headerIP;
        }
        return ip;
    }


    public static String getRealIp(HttpServletRequest request){
        String ip;
        // 有的user可能使用代理，为处理用户使用代理的情况，使用x-forwarded-for
        if  (request.getHeader("x-forwarded-for") == null)  {
            ip = request.getRemoteAddr();
        }  else  {
            ip = request.getHeader("x-forwarded-for");
        }
        if  ("127.0.0.1".equals(ip))  {
            try {
                // 获取本机真正的ip地址
                ip = InetAddress.getLocalHost().getHostAddress();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ip;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private void render(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(o);
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

}
