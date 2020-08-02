package com.hysterix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hysterix.entity.CalculateRequest;
import com.hysterix.service.HysterixService;

@RestController
@RequestMapping("api")
public class HysterixController {
	
	
	@Autowired
	HysterixService hysterixService;

	
	@RequestMapping(value = "calculate",method=RequestMethod.POST)
	public String calculate(@RequestBody CalculateRequest calculateRequest) {
		
		String result = hysterixService.calculate(calculateRequest);
		
		return result;
	}
	
	@PostMapping("add")
	public String add(@RequestBody CalculateRequest calculateRequest) {
		
		String result = hysterixService.add(calculateRequest);
		
		return result;
	}
}
