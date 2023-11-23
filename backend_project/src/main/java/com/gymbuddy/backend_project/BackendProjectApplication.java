package com.gymbuddy.backend_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.gymbuddy.backend_project.entity")
public class BackendProjectApplication
{

	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}
}