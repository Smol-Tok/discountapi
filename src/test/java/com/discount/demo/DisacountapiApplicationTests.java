package com.discount.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import com.discount.demo.controller.DiscountController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DisacountapiApplicationTests {
	
	
	@Autowired
	private DiscountController controller;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	String url = "http://localhost:7770/api/v1/paybill";

	@Test
	void contextLoads() throws Exception {
		
		assertThat(controller).isNotNull();
	}
	
	
	@Test
	/**
	 * 
	 * The percentage based discounts do not apply on groceries
	 * For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45
		as a discount).
	 */
	void discountGrocery() {
		
		Map< String, String> params = new HashMap<String,String>();
		params.put("billType", "grocery");
		params.put("amount", "990");
		params.put("customerID", "1");
	
		
		ResponseEntity<HashMap > post = restTemplate.postForEntity(url,  params, HashMap.class);
		
	
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("discount", "$ 45.0");
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("netpayment", "$ 945.0");
	
	}

	@Test
	/**
	 * Employee discount is 30%
	 * $1000 bill discounts $ 300
	 *  Expected output {   "Employee": true,      "discount": "$ 300.0",     "netpayment": "$ 700.0"}
	 */
	void discountBillOnEmployee() {
		
		Map< String, String> params = new HashMap<String,String>();
		params.put("billType", "bill");
		params.put("amount", "1000");
		params.put("customerID", "1");
		
		ResponseEntity<HashMap > post = restTemplate.postForEntity(url,  params, HashMap.class);
		
	
		
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("discount", "$ 300.0");
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("netpayment", "$ 700.0");
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("Employee", true);
		
	}
	
	@Test
	/**
	 * Affilliate discount is 10%
	 * $1000 bill discounts $ 100
	 *  Expected output {    "discount": "$ 100.0",     "netpayment": "$ 900.0",     "Affiliate": true}
	 */
	void discountBillOnAffilliate() {
		
		Map< String, String> params = new HashMap<String,String>();
		params.put("billType", "bill");
		params.put("amount", "1000");
		params.put("customerID", "5");
		
		ResponseEntity<HashMap > post = restTemplate.postForEntity(url,  params, HashMap.class);
		
		
		
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("discount", "$ 100.0");
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("netpayment", "$ 900.0");
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("Affiliate", true);
		
	}
	
	@Test
	/**
	 * If the user has been a customer for over 2 years, he gets a 5% discount
	 * $1000 bill discounts $ 50
	 *  Expected output{  "discount": "$ 50.0",     "Number of years as customer": 17,     "netpayment": "$ 950.0",    "dateRegistered": "2004-04-02",     "Date": "2021-09-19"}
	 */
	void discountBillOnTwoYearsCustomer() {
		
		Map< String, String> params = new HashMap<String,String>();
		params.put("billType", "bill");
		params.put("amount", "1000");
		params.put("customerID", "6");
		
		ResponseEntity<HashMap > post = restTemplate.postForEntity(url,  params, HashMap.class);
		
		//System.out.println(post);
		
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("discount", "$ 50.0");
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("netpayment", "$ 950.0");
		assertThat(post.getBody()).hasFieldOrPropertyWithValue("Number of years as customer", 17);
		
	}
}
