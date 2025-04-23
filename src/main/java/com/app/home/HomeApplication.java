package com.app.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HomeApplication {

	public static void main(String[] args) {

		SpringApplication.run(HomeApplication.class, args);
	}

}
