package com.example.umc10thweek4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc10thWeek4Application {

	public static void main(String[] args) {
		SpringApplication.run(Umc10thWeek4Application.class, args);
	}

}
