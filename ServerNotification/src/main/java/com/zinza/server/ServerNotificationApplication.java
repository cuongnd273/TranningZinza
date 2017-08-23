package com.zinza.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.zinza.server.api"})
public class ServerNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerNotificationApplication.class, args);
	}
}
