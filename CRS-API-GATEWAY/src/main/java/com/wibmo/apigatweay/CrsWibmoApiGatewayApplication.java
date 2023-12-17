package com.crs.apigatweay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.crs.configuration.SpringCloudConfig;


@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@Import({SpringCloudConfig.class})
@EnableEurekaClient
public class CrsWibmoApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsWibmoApiGatewayApplication.class, args);
	}

}
