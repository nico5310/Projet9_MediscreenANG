package com.nico5310.assessmentMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients("com.nico5310.assessmentMicroservice")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AssessmentApplication {

	public static void main(String[] args) {

		SpringApplication.run(AssessmentApplication.class, args);
	}

}
