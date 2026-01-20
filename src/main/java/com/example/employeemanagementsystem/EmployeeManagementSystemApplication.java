package com.example.employeemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeManagementSystemApplication.class, args);

		Integer x=5;
		chack(x);
		System.out.println(x);

	}

	public static void chack(Integer num)
	{
		System.out.println(num);
		 num=20;
	}

}
