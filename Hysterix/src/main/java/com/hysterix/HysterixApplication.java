package com.hysterix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = HysterixApplication.class)
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HysterixApplication {

	public static void main(String[] args) {
		SpringApplication.run(HysterixApplication.class, args);
	}

}
