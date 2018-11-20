package com.ztx.world.common.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	// 标注该方法体为后置通知,当目标方法执行成功后执行该方法体
	@AfterReturning("within(com.ztx.world.*.controller) && @annotation(logOperate)")
	public void addLogSuccess(JoinPoint joinPoint, LogOperate logOperate) {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&");
		Object[] parames = joinPoint.getArgs();// 获取目标方法体参数
		String className = joinPoint.getTarget().getClass().toString();
		// 获取目标类名
		className = className.substring(className.indexOf("com"));
		// 获取目标方法签名
		String signature = joinPoint.getSignature().toString();
		String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&");
	}
	
}
