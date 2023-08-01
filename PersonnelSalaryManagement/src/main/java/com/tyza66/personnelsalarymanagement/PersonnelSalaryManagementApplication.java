package com.tyza66.personnelsalarymanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tyza66.personnelsalarymanagement.mapper")
public class PersonnelSalaryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelSalaryManagementApplication.class, args);
	}

}
