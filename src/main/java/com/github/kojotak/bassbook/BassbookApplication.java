package com.github.kojotak.bassbook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BassbookApplication implements CommandLineRunner {

	@Value("${spring.application.name}")
	private String applicationName;

	public static void main(String[] args) {
		SpringApplication.run(BassbookApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Loading " + applicationName);
	}


}
