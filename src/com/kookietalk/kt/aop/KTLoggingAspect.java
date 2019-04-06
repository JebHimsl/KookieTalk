package com.kookietalk.kt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class KTLoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.kookietalk.kt.controllers.*.*(..))")
	private void forControllersPackage() {
		
	}
	
	@Pointcut("execution(* com.kookietalk.kt.services.*.*(..))")
	private void forServicesPackage() {
		
	}
	
	@Pointcut("execution(* com.kookietalk.kt.dao.*.*(..))")
	private void forDAOPackage() {
		
	}
	
	@Pointcut("forControllersPackage() || forServicesPackage() || forDAOPackage()")
	private void forApplication() {
		
	}
	
	@Before("forApplication()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("Information: in @Before Advice:  Calling the method: " + methodName);
	}
	
	@AfterReturning(pointcut="forApplication()", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("Information: in @AfterReturning Advice:  From the method: " + methodName);
		logger.info("Information: data returned by method: " + result);
	}
}
