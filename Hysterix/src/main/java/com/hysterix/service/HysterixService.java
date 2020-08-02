package com.hysterix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hysterix.client.CaclulateClient;
import com.hysterix.entity.CalculateRequest;

@Service
public class HysterixService {
	
	
	@Autowired
	CaclulateClient calculateClient;

	public String calculate(CalculateRequest calculateRequest) {
		
		int result = calculateRequest.getNumber1()+ calculateRequest.getNumber2();
		
		return String.valueOf(result);
		
	}

	public String add(CalculateRequest calculateRequest) {
		
		String resp = calculateClient.calculate(calculateRequest);
		
		return resp;
	}

	
}
