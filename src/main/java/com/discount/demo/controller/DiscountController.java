package com.discount.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discount.demo.model.Customer;
import com.discount.demo.model.CustomerService;

@RestController
@RequestMapping("api/v1/paybill")
public class DiscountController {
	

	private CustomerService service;

	public DiscountController(CustomerService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers() {
		
		
		return ResponseEntity.ok().body((service.getCustomers()));
		
	}
	
	@PostMapping()
	public ResponseEntity<Map> paybill(@RequestBody Map<String, String> input ) {
		
		
		return  ResponseEntity.ok().body(service.calcaluteNetPayment(input));
		
	}
	
}
