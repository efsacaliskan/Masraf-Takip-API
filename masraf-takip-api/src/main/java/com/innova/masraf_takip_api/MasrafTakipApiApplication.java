package com.innova.masraf_takip_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;*/



@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement

@ComponentScan(basePackages = "com.innova.masraf_takip_api")
/*@OpenAPIDefinition(info = @Info(version = "1.0.0", 
title = "Transaction Management System API", 
description = "This is a simple API",
contact = @Contact(email="efsa.caliskan@ozu.edu.tr", name = "Efsa Caliskan")))*/

public class MasrafTakipApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasrafTakipApiApplication.class, args);
	}

}
