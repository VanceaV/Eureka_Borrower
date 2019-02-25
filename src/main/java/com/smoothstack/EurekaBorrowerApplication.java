package com.smoothstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaBorrowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaBorrowerApplication.class, args);
	}

}
