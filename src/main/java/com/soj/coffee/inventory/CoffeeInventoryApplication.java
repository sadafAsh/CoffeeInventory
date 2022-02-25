package com.soj.coffee.inventory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeInventoryApplication {
static final Logger logger= LogManager.getLogger(CoffeeInventoryApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(CoffeeInventoryApplication.class, args);
		logger.info("hello");
	}

}
