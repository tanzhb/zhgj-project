package com.congmai.zhgj.log.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.OperateLog;
import com.congmai.zhgj.web.model.TakeDeliveryParams;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.OperateLogService;
import com.congmai.zhgj.log.annotation.OperationLog;

@Aspect
@Component
public class OperateLogAop{
    @Autowired
    OperateLogService operateLogService;
    HttpServletRequest request = null;
    Logger logger = Logger.getLogger(OperateLogAop.class);
    ThreadLocal<Long> time = new ThreadLocal<Long>();
    //用于生成操作日志的唯一标识，用于业务流程审计日志调用
    public static ThreadLocal<String> tag = new ThreadLocal<String>();
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    //声明AOP切入点，凡是使用了OperationLog的方法均被拦截
    @Pointcut("@annotation(com.congmai.zhgj.log.annotation.OperationLog)")
    public void log() {
        System.out.println("我是一个切入点");
    }

    /**
     * 在所有标注@Log的地方切入
     * @param joinPoint
     */
    @Before("log()")
    public void beforeExec(JoinPoint joinPoint) {
        time.set(System.currentTimeMillis());   
        info(joinPoint);
        //设置日志记录的唯一标识号
        tag.set(ApplicationUtils.random32UUID());
        request=  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    }
    @After("log()")
    public void afterExec(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        logger.debug("标记为" + tag.get() + "的方法" + method.getName()
                + "运行消耗" + (System.currentTimeMillis() - time.get()) + "ms");   
    }
    //在执行目标方法的过程中，会执行这个方法，可以在这里实现日志的记录
    @SuppressWarnings("rawtypes")
	@Around("log()")
    public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {
        Object ret = pjp.proceed();
        try {
            Object[] orgs = pjp.getArgs();
            OperateLog valueReturn = null;
            for (int i = 0; i < orgs.length; i++) {
                if(orgs[i] instanceof OperateLog){
                    valueReturn = (OperateLog) orgs[i];
                }   
            }   
            if(valueReturn==null){
                valueReturn =  new OperateLog();
            }
            if(valueReturn!=null&&request!=null){

                MethodSignature ms = (MethodSignature) pjp.getSignature();
                Method method = ms.getMethod();
                //获取注解的操作日志信息
                OperationLog log = method.getAnnotation(OperationLog.class);

                User user = UserUtil.getUserFromSession();
                /*if(user==null){
                	user = (User) ((HttpSession)orgs[0]).getAttribute(Constants.CURRENT_USER);
                }*/
                	
                valueReturn.setMoudleCode(log.moudleCode());
                valueReturn.setMoudleName(log.moudleName());
                valueReturn.setOperateType(log.operateType());
                valueReturn.setOperationDesc(log.operationDesc());
                if(user!=null){
                	valueReturn.setOperator(user.getUserName());
                    valueReturn.setCreator(user.getUserId().toString());
                }
                valueReturn.setOperationTime(new Date());
                valueReturn.setOperateResult(SUCCESS);
                valueReturn.setRequestIp(getRemoteHost(request));
                valueReturn.setRequestUrl(request.getRequestURI());
                valueReturn.setServerIp(request.getLocalAddr());
                
                //替换动态参数，获取流水号
                if(log.objectSerial()!=null){
                	if(orgs[0] instanceof Map){
                    	valueReturn.setObjectSerial(((Map)orgs[0]).get(strFormat(log.objectSerial())).toString());
                    }else if(orgs[0] instanceof String){
                    	valueReturn.setObjectSerial(orgs[0].toString());
                    }else if(orgs[0] instanceof Object){
                    	valueReturn.setObjectSerial(reflectObject(orgs[0],strFormat(log.objectSerial())));
                    }
                }
                
                
                
                valueReturn.setSerialNum(tag.get());
                //保存操作日志
                operateLogService.insert(valueReturn);
            }else{
                logger.info("不记录日志信息");
            }
            //保存操作结果    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    //记录异常日志
    @AfterThrowing(pointcut = "log()",throwing="e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            info(joinPoint);
            Object[] orgs = joinPoint.getArgs();
            OperateLog valueReturn = null;
            for (int i = 0; i < orgs.length; i++) {
                if(orgs[i] instanceof OperateLog){
                    valueReturn = (OperateLog) orgs[i];
                }           
            }
            if(valueReturn==null){
                valueReturn =  new OperateLog();
            }
            if(valueReturn!=null&&request!=null){
                MethodSignature ms = (MethodSignature) joinPoint.getSignature();
                Method method = ms.getMethod();
                OperationLog log = method.getAnnotation(OperationLog.class);
                
/*                valueReturn.setBussType();
                valueReturn.setBussTypeDesc();*/
                User user = UserUtil.getUserFromSession();
                valueReturn.setMoudleCode(log.moudleCode());
                valueReturn.setMoudleName(log.moudleName());
                valueReturn.setOperateType(log.operateType());
                valueReturn.setOperationDesc(log.operationDesc());
                valueReturn.setOperator(user.getUserName());
                valueReturn.setCreator(user.getUserId().toString());
                valueReturn.setOperationTime(new Date());
                valueReturn.setOperateResult(FAILURE);
                String errMes = e.getMessage();
                if(errMes!=null && errMes.length()>800){
                    errMes = errMes.substring(0, 800);
                }
                valueReturn.setErrorMessage(errMes);
                valueReturn.setRequestIp(getRemoteHost(request));
                valueReturn.setRequestUrl(request.getRequestURI());
                valueReturn.setServerIp(request.getLocalAddr());
                
                //替换动态参数，获取流水号
                if(log.objectSerial()!=null){
                	if(orgs[0] instanceof Map){
                    	valueReturn.setObjectSerial(((Map)orgs[0]).get(strFormat(log.objectSerial())).toString());
                    }else if(orgs[0] instanceof String){
                    	valueReturn.setObjectSerial(orgs[0].toString());
                    }else if(orgs[0] instanceof Object){
                    	valueReturn.setObjectSerial(reflectObject(orgs[0],strFormat(log.objectSerial())));
                    }
                }
                
                valueReturn.setSerialNum(tag.get());
                operateLogService.insert(valueReturn);
            }else{
                logger.info("不记录日志信息");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
         

    private void info(JoinPoint joinPoint) {
        logger.debug("--------------------------------------------------");
        logger.debug("King:\t" + joinPoint.getKind());
        logger.debug("Target:\t" + joinPoint.getTarget().toString());
        Object[] os = joinPoint.getArgs();
        logger.debug("Args:");
        for (int i = 0; i < os.length; i++) {
            logger.debug("\t==>参数[" + i + "]:\t" + os[i].toString());
        }
        logger.debug("Signature:\t" + joinPoint.getSignature());
        logger.debug("SourceLocation:\t" + joinPoint.getSourceLocation());
        logger.debug("StaticPart:\t" + joinPoint.getStaticPart());
        logger.debug("--------------------------------------------------");
    }
    
    /** 
     * 反射对象 
     * @throws Exception 
     */  
    public String reflectObject(Object obj,String fieldName) throws Exception {  
    	String value="";
    	Field[] field = obj.getClass().getDeclaredFields();  
        // 遍历所有属性  
        for (int j = 0; j < field.length; j++) {  
            // 获取属性的名字  
            String name = field[j].getName();  
            if(name.equals(fieldName)){
            	 // 将属性的首字符大写，方便构造get，set方法  
                name = name.substring(0, 1).toUpperCase() + name.substring(1);  
                // 如果type是类类型，则前面包含"class "，后面跟类名  
                System.out.println("属性为：" + name);  
                Method m = obj.getClass().getMethod("get" + name);  
                // 调用getter方法获取属性值  
                value = (String) m.invoke(obj);  
                break;
            }
        }  
        return value;  
    } 
    /** 
     * 获取日志信息中的动态参数，然后替换 
     * @param str 
     * @return 
     */  
    private String strFormat(String str){  
        Pattern pattern = Pattern.compile("\\{([^\\}]+)\\}");   
        Matcher matcher = pattern.matcher(str);   
        String t = "";
        while(matcher.find()){   
            t = matcher.group(1);   
        }  
        return t;  
    }  
    /**
     * 获取远程客户端Ip
     * @param request
     * @return
     */
    public static String getRemoteHost(javax.servlet.http.HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }  
    
    /*private static String[] types = { "java.lang.Integer", "java.lang.Double",  
        "java.lang.Float", "java.lang.Long", "java.lang.Short",  
        "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",  
        "java.lang.String", "int", "double", "long", "short", "byte",  
        "boolean", "char", "float" };  
    
    private static String writeLogInfo(String[] paramNames, JoinPoint joinPoint){  
        Object[] args = joinPoint.getArgs();  
        StringBuilder sb = new StringBuilder();  
        boolean clazzFlag = true;  
        for(int k=0; k<args.length; k++){  
            Object arg = args[k];  
            sb.append(paramNames[k]+" ");  
            // 获取对象类型  
            String typeName = arg.getClass().getTypeName();  
              
            for (String t : types) {  
                if (t.equals(typeName)) {  
                    sb.append("=" + arg+"; ");  
                }  
            }  
            if (clazzFlag) {  
                sb.append(getFieldsValue(arg));  
            }  
        }  
        return sb.toString();  
    }  
    *//** 
     * 得到方法参数的名称 
     * @param cls 
     * @param clazzName 
     * @param methodName 
     * @return 
     * @throws NotFoundException 
     *//*  
    private static String[] getFieldsName(Class cls, String clazzName, String methodName) throws NotFoundException{  
        ClassPool pool = ClassPool.getDefault();  
        //ClassClassPath classPath = new ClassClassPath(this.getClass());  
        ClassClassPath classPath = new ClassClassPath(cls);  
        pool.insertClassPath(classPath);  
          
        CtClass cc = pool.get(clazzName);  
        CtMethod cm = cc.getDeclaredMethod(methodName);  
        MethodInfo methodInfo = cm.getMethodInfo();  
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
        if (attr == null) {  
            // exception  
        }  
        String[] paramNames = new String[cm.getParameterTypes().length];  
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
        for (int i = 0; i < paramNames.length; i++){  
            paramNames[i] = attr.variableName(i + pos); //paramNames即参数名  
        }  
        return paramNames;  
    }  
    *//** 
     * 得到参数的值 
     * @param obj 
     *//*  
    public static String getFieldsValue(Object obj) {  
        Field[] fields = obj.getClass().getDeclaredFields();  
        String typeName = obj.getClass().getTypeName();  
        for (String t : types) {  
            if(t.equals(typeName))  
                return "";  
        }  
        StringBuilder sb = new StringBuilder();  
        sb.append("【");  
        for (Field f : fields) {  
            f.setAccessible(true);  
            try {  
                for (String str : types) {  
                    if (f.getType().getName().equals(str)){  
                        sb.append(f.getName() + " = " + f.get(obj)+"; ");  
                    }  
                }  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
        }  
        sb.append("】");  
        return sb.toString();  
    }  
    private void getParamterName(String clazzName, String methodName)  
            throws NotFoundException {  
        ClassPool pool = ClassPool.getDefault();  
        ClassClassPath classPath = new ClassClassPath(this.getClass());  
        pool.insertClassPath(classPath);  
  
        CtClass cc = pool.get(clazzName);  
        CtMethod cm = cc.getDeclaredMethod(methodName);  
        MethodInfo methodInfo = cm.getMethodInfo();  
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute  
                .getAttribute(LocalVariableAttribute.tag);  
        if (attr == null) {  
            // exception  
        }  
        String[] paramNames = new String[cm.getParameterTypes().length];  
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
        for (int i = 0; i < paramNames.length; i++)  
            paramNames[i] = attr.variableName(i + pos);  
        // paramNames即参数名  
        for (int i = 0; i < paramNames.length; i++) {  
            System.out.println(paramNames[i]);  
        }  
    }  */
}