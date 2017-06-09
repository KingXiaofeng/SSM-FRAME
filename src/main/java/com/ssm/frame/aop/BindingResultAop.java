package com.ssm.frame.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

/**
 *
 * @ClassName: BindingResultAop.java
 * @Description: AOP 类
 *
 * @version: v1.0.0
 * @author: wangxf
 * @date: 2017年5月2日 上午11:54:21
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2017年5月2日     wangxf           v1.0.0               修改原因
 */
public class BindingResultAop {

    private final Logger LOGGER = LoggerFactory.getLogger(BindingResultAop.class);

    /**
     *
     * @Function: BindingResultAop.java
     * @Description: Controller的AOP切面类
     *
     * @param:
     * @return：
     * @throws：
     *
     * @version: v1.0.0
     * @author: wangxf
     * @date: 2017年5月2日 下午1:17:52
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2017年5月2日     wangxf           v1.0.0               修改原因
     */
    @Pointcut("execution(* com.ssm.frame.web.*.*(..))")
    public void aopMethod(){}

    /**
     *
     * @Function: BindingResultAop.java
     * @Description: 当执行上面的aopMethod()的方法时， aspectJ切面通过ProceedingJoinPoint想要获取当前执行的方法
     *
     * @param: ProceedingJoinPoint
     * @return： Object
     * @throws：异常描述
     *
     * @version: v1.0.0
     * @author: wangxf
     * @date: 2017年5月2日 下午1:15:00
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2017年5月2日     wangxf           v1.0.0               修改原因
     */
    @Around("aopMethod()")
    public Object  around(ProceedingJoinPoint joinPoint) throws Throwable{

        // 日志记录
        LOGGER.info("before method invoking!");

        // Spring验证的错误返回 BindingResult
        BindingResult bindingResult = null;

        // 循环获取到的 joinPoint.getArgs() 的参数
        for(Object arg:joinPoint.getArgs()){
            // 判断是否是 BindingResult 类型,如果是，将其赋值给bindingResult
            if(arg instanceof BindingResult){
                bindingResult = (BindingResult) arg;
            }
        }
        if(bindingResult != null){

            // 如果有任何错误，返回为true,否则为false
            if(bindingResult.hasErrors()){
                // 错误信息
                String errorInfo="["+bindingResult.getFieldError().getField()+"]"+bindingResult.getFieldError().getDefaultMessage();
                return 404;
            }
        }
        return joinPoint.proceed();
    }
}
