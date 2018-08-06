package com.example.integs;

import com.example.pojo.Log;
import com.example.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //把整个log中的参数，交给logUtil来获取，并返回log对象
        Log log = null;
        try {
            log = LoggerUtil.getLog(httpServletRequest);
//        }catch (GeneralException g){
//            logger.warn("logger",g.getMessage());
        }catch (Exception e){
            logger.error("logger",e.getMessage());
        }
        httpServletRequest.setAttribute(LoggerUtil.LOG_OPERATE,log);
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //返回视图时，插入操作日志
//        LogMapper logMapper = getMapper(LogMapper.class,httpServletRequest);
        Log log = (Log) httpServletRequest.getAttribute(LoggerUtil.LOG_OPERATE);
        if(log == null){
            logger.warn("日志信息为空",log);
        }else{
//            logMapper.insert(log);
        }
    }
    private <T> T getMapper(Class<T> clazz,HttpServletRequest request)
    {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }

}