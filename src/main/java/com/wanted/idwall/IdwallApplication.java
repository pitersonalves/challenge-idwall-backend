package com.wanted.idwall;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Wanted Person FBI and Interpool", version = "1",
		description = "API for integration of wanted person from FBI and Interpool"))
public class IdwallApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdwallApplication.class, args);
	}

}
