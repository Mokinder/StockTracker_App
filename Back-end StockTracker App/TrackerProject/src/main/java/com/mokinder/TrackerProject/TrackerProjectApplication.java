package com.mokinder.TrackerProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrackerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerProjectApplication.class, args);
	}

}
