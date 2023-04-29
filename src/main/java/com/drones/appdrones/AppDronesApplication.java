package com.drones.appdrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppDronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppDronesApplication.class, args);
	}

}
