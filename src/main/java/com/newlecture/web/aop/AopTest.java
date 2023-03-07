package com.newlecture.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class AopTest {
	
	@Around("execution(* com.newlecture.web..*(..))")
	public Object e(ProceedingJoinPoint p) throws Throwable {
		System.out.println("하기 전");
		try {
			return p.proceed();
		} finally {
			System.out.println("하고 난 후");
		}
	}
}
