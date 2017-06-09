package com.ssm.frame.exception;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * Copyright: Copyright (c) 2017 LanRu-Caifu
 *
 * @ClassName: GlobalExceptionResolver.java
 * @Description: 错误信息统一处理 对未处理的错误信息做一个统一处理
 * @author:wangxf
 * @date: 2017年4月27日 下午4:46:47
 */

// 把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver{

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        logger.error("访问 " + request.getRequestURI() + " 发生错误，错误信息为：" + ex.getMessage());

        // 这里有2种选择
        // 跳转到定制化的错误页面
		/*
		 * ModelAndView error = new ModelAndView("error");
		 * error.addObject("exMsg", ex.getMessage()); error.addObject("exType",
		 * ex.getClass().getSimpleName().replace("\"", "'"));
		 */
        // 返回json格式的错误信息
        try {
            PrintWriter writer = response.getWriter();
            String result = ex.getMessage();
            writer.write(JSONObject.toJSONString(result));
            writer.flush();
        } catch (IOException e) {
            logger.error("exception : " + e);
        }
        return null;
    }
}
