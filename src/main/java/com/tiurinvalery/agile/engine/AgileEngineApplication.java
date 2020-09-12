package com.tiurinvalery.agile.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tiurinvalery.agile.engine.controller", "com.tiurinvalery.agile.engine.config", "com.tiurinvalery.agile.engine.repo", "com.tiurinvalery.agile.engine.service"})
public class AgileEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgileEngineApplication.class, args);
	}

}
