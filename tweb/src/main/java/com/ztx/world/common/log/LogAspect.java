package com.ztx.world.common.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ztx.world.base.entity.Log;
import com.ztx.world.base.mapper.LogMapper;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.utils.IPAndMacUtil;

@Aspect
@Component
public class LogAspect {
	
	private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Autowired
	private LogMapper logMapper;

    /**
     * 切点
     */
    @Pointcut("execution(* com.ztx.world.*.controller.**))")
    public void controllerPointcut() {}

    /**
     * 切面
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
    	Log log = new Log();
    	log.setStatus(BaseConstants.UNDELETE_STATUS);
    	// 设置操作开始时间
    	Date startTime = new Date();
    	long startTimeLong = startTime.getTime();
    	log.setOperateTime(startTime);
    	// 设置IP和MAC地址
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        try {
			log.setOperateIp(IPAndMacUtil.getIP(request));
			log.setOperateIp(IPAndMacUtil.getMACAddress(log.getOperateIp()));
		} catch (Exception e) {
			logger.error("Get IP or MAC error.", e);
		}
        // 如果已经登录,设置操作人id
        CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
        if(customSession != null){
        	log.setOperateUserId(customSession.getUserId());
        }
        log.setModelType(point.getTarget().getClass().getName());
        log.setOperateType(point.getSignature().getName());
        
        // 设置操作对象(传入参数)
        Object[] params = point.getArgs();
        log.setOperateObject(params.toString());

        Object object;
        try {
            object = point.proceed();
            // 设置操作结果(方法返回结果)
            log.setOperateResult(object.toString());
        	// 如果操作前未登录,操作后再次设置操作人id
        	if(customSession == null){
        		customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
        		log.setOperateUserId(customSession.getUserId());
        	}
        	// 设置操作持续时间
        	long endTimeLong = new Date().getTime();
        	log.setTimeSpan(endTimeLong - startTimeLong);
        	logMapper.insertSelective(log);
        } catch (Exception exception) {
            // 设置操作结果
            log.setOperateResult(exception.getMessage());
        	// 如果操作前未登录,操作后再次设置操作人id
        	if(customSession == null){
        		customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
        		log.setOperateUserId(customSession.getUserId());
        	}
        	// 设置操作持续时间
        	long endTimeLong = new Date().getTime();
        	log.setTimeSpan(endTimeLong - startTimeLong);
        	logMapper.insertSelective(log);
            throw exception;
        }
        return object;
    }
	
}
