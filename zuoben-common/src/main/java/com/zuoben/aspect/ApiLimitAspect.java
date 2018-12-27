package com.zuoben.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ApiLimitAspect {

	@Pointcut("execution(* com.sxit..*.*(..))")
	public void init(){

	}

	@Before(value="init()")
	public void before(){
		System.out.println("方法执行前执行.....");
	}

	@AfterReturning(value="init()")
	public void afterReturning(){
		System.out.println("方法执行完执行.....");
	}

	@AfterThrowing(value="init()")
	public void throwss(){
		System.out.println("方法异常时执行.....");
	}

	@After(value="init()")
	public void after(){
		System.out.println("方法最后执行.....");
	}

	@Around(value="init()")
	public Object around(ProceedingJoinPoint pjp){
		System.out.println("方法环绕start.....");
		Object o = null;
		try {
			o = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("方法环绕end.....");
		return o;
	}
}
