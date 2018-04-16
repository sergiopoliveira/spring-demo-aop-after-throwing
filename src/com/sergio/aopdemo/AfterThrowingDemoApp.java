package com.sergio.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sergio.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		List<Account> theAccounts = null;

		try {
			
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
			
		} catch (Exception exc) {
			System.out.println("\nMain Program...caught exception: " + exc);
		}
		
		// display the accounts
		System.out.println("\n\nMain Program: AfterThrowingDemoApp");
		System.out.println("---");

		for (Account a : theAccounts) {
			System.out.println(a);
		}

		System.out.println("\n");

		// close the context
		context.close();
	}

}
