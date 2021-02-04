package com.project.briefing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EntityScan("com.mine.alien")
@EnableJpaRepositories("com.mine.demo.control") 
@SpringBootApplication
public class BriefingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BriefingApplication.class, args);
	}

}
