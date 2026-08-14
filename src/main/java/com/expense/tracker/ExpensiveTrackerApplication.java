package com.expense.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpensiveTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensiveTrackerApplication.class, args);
	}

}

//step 1:- register users
// hit on :- http://localhost:23456/register

//user:- ravi@test.com  password:- 12345

// after register login :- http://localhost:23456/login
