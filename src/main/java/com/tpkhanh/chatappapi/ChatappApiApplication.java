package com.tpkhanh.chatappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChatappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatappApiApplication.class, args);
	}

}
