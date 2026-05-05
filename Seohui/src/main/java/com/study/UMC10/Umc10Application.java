package com.study.UMC10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc10Application {

	public static void main(String[] args) {
		SpringApplication.run(Umc10Application.class, args);
	}

}
