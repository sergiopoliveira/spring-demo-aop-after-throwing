package com.sergio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {
	
	@Before("com.sergio.aopdemo.aspect.LuvAopExpressions.forDaoPackageNotGetterSetter()")
	public void loggingToCloudAsync() {
		System.out.println("\n=====>>> Logging to Cloud in async fashion");
	}
}
