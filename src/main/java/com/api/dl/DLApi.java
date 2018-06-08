package com.api.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class DLApi {

	public static void main(String[] args) {
		SpringApplication.run(DLApi.class, args);
	}
}
