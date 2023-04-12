package com.devopsinfinet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;





@SpringBootApplication
@EnableZipkinServer
public class DevopsinfinetApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsinfinetApplication.class, args);
	}

}
