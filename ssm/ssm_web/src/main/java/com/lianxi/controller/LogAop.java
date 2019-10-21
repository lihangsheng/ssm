package com.lianxi.controller;

import com.lianxi.domain.SysLog;
import com.lianxi.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class LogAop {
    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime;//开始的时间
    private Class clazz;//访问的类
    private Method method;//访问的方法



   //前置通知  获取执行开始的时间，方法，类
    @Before("execution(* com.lianxi.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
          visitTime=new Date();//当前时间就是开始访问的时间
          clazz=jp.getTarget().getClass();//具体要访问的类
          String methodName = jp.getSignature().getName();//获取访问方法的名字
        Object[] args = jp.getArgs();//获取访问的方法参数

        //获取了具体执行的方法的Method对象
        if(args==null||args.length==0){
            method=clazz.getMethod(methodName);//只能获取无参数的方法
        }else{
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i <args.length ; i++) {
                classArgs[i]=args[i].getClass();
            }
            
            method= clazz.getMethod(methodName,classArgs);
        }

    }

    //后置通知
    @After("execution(* com.lianxi.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();//获取访问的时长

        String url="";
        //获取url
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            //1.获取类上的@RequestMapping("/role")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String[] classValue = classAnnotation.value();

              //2.获取方法上的 @RequestMapping("/xxx")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methoValue = methodAnnotation.value();

                    url=classValue[0]+methoValue[0];

                    //3.获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取当前登录的用户
                    SecurityContext context= SecurityContextHolder.getContext();//从上下文获取了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    //或者通过session来获取当前用户
                    // request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

                    //将日志相关信息封装到SysLog对象中
                    SysLog sysLog=new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    String id = UUID.randomUUID().toString();
                    sysLog.setId(id);
                    //调用service完成操作
                    sysLogService.save(sysLog);
                }
            }
        }

    }
}
