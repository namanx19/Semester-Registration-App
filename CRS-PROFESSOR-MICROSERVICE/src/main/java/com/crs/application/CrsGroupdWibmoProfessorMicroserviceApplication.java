package com.crs.application;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories("com.crs.repository")
@EntityScan(basePackages = "com.crs.entity")
@EnableSwagger2
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan("com.crs.*")
@Configuration
@EnableEurekaClient
@SpringBootApplication
public class CrsGroupdWibmoProfessorMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoProfessorMicroserviceApplication.class, args);
	}

}
