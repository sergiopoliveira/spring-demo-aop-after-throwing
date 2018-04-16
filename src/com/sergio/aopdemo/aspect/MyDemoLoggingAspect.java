package com.sergio.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sergio.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.sergio.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====> Executing @AfterThrowing on method: " + method);

		// log the exception
		System.out.println("\n====>>> The exception is: " + theExc);
	}

	@AfterReturning(pointcut = "execution(* com.sergio.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====> Executing @AfterReturning on method: " + method);

		// print out the results of the method call
		System.out.println("\n=====> Result is: " + result);

		// let's post-process the data... let's modify it

		// convert the account name to uppercase
		convertAccountNamesToUpperCase(result);

		System.out.println("\n=====> Result is: " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		// loop through accounts
		for (Account a : result) {

			// get uppercase version of name
			String theUpperName = a.getName().toUpperCase();

			// update the name on the account
			a.setName(theUpperName);
		}
	}

	@Before("com.sergio.aopdemo.aspect.LuvAopExpressions.forDaoPackageNotGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		System.out.println("\n=====>>> Executing @Before advice on addAccount()");

		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		System.out.println("Method: " + methodSig);

		// display method arguments
		Object[] args = theJoinPoint.getArgs();

		// loop thru args
		for (Object tempArg : args) {
			System.out.println(tempArg);

			if (tempArg instanceof Account) {

				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;

				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());

			}
		}

	}
}
