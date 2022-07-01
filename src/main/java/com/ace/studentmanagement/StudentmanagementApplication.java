package com.ace.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ace.studentmanagement.helpers.UserHelper;

@SpringBootApplication
public class StudentmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementApplication.class, args);
		String id = new UserHelper().idGenerator();
		System.out.println("id is "+ id);
	}

}
