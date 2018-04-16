package com.sergio.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sergio.aopdemo.dao.AccountDAO;
import com.sergio.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO =
				context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO theMembershipDAO = 
				context.getBean("membershipDAO", MembershipDAO.class);
	
		
		Account myAccount = new Account();
		myAccount.setName("Sergio");
		myAccount.setLevel("Platinum");

		// call the business method
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		// call the accountdao getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
		
		// call the membership methods
		theMembershipDAO.addAccount();
		theMembershipDAO.goToSleep();
	
		// close the context
		context.close();
	}

}
