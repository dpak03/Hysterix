package com.hysterix.client;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hysterix.entity.CalculateRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class CaclulateClient {

	private Logger logger = LoggerFactory.getLogger(CaclulateClient.class);

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "calculateFallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),	
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public String calculate(CalculateRequest calculateRequest) {

		HttpEntity<String> resp = null;
		try {
			final String uri = "http://localhost:8080/api/calculate";

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<CalculateRequest> request = new HttpEntity<CalculateRequest>(calculateRequest, headers);

			resp = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);

			logger.info(resp.getBody());

		} catch (Exception excep) {

			logger.error("Exception while invoking Calculate API" + excep.getMessage());
		}

		return resp.getBody();
	}

	public String calculateFallback(CalculateRequest calculateRequest, Throwable thro) {

		return "Callback method is called" + thro;
	}

}
