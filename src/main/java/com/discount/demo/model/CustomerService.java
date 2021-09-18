package com.discount.demo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/*
 * Handles business logic of the application
 */
@Service
@EnableMapRepositories
@ComponentScan({"com.discount.demo.model"})
public class CustomerService {
	
	static final double EMPLOYEE_DISCOUNT =0.30;
	static final double AFFILIATE_DISCOUNT =0.10;
	static final double YEARS_DISCOUNT = 0.05;
	static final double BILL_DISCOUNT_RATE = 5;
	
	//keeps records on on customer
	
	private final CrudRepository<Customer, Long> customers;
	
	public  CustomerService(CrudRepository<Customer, Long> customers) {
		
		this.customers = customers;
		
		//create dump customers to use for testing
			this.customers.save(new Customer(1,"John Wick", true,false, "2021-04-02"));
			this.customers.save(new Customer(2,"Spider Man", true,false,  "2010-04-02"));
			this.customers.save(new Customer(3,"Super Man", true,false,  "2004-04-02" ));
			this.customers.save(new Customer(4,"Batma", true,true, "2021-04-02" ));
			this.customers.save(new Customer(5,"John Wick", true,true, "2001-04-02" ));
			this.customers.save(new Customer(6,"Green Goblin", false,false, "2004-04-02" ));
		
	}

	/*
	 * Get a list of all customers
	 */
	public List<Customer> getCustomers() {
		
		List<Customer> customers = new ArrayList<>();
		
		Iterable<Customer> result = this.customers.findAll();
		
		 result.forEach(customers::add);
		
		 return customers;
		
	}
	
	
	public Optional<Customer> getCustomer(int id) {
		
		
		return customers.findById(new Long(id));
		
	}
	
	public Map<String,String > calcaluteNetPayment(Map input) {
		
	 Customer c =	getCustomer( new Integer(input.get("customerID").toString()).intValue()).get();
	 
	 Map output = new HashMap();
	 double discount =0;
	 double amount = Float.parseFloat(input.get("amount").toString() );
	 long years =0;
	 
	 
	  if (input.get("billType").toString().equalsIgnoreCase("grocery"))
	  {
		  
		   
		  	discount = Math.floor(amount/100) * BILL_DISCOUNT_RATE;
	    
	  } else if (input.get("billType").toString().equalsIgnoreCase("bill")) {
		  
	 
			  	//convert String to LocalDate
			 	LocalDate start = LocalDate.parse(c.getDateRegistered());

	 			years = java.time.temporal.ChronoUnit.YEARS.between(  LocalDate.now() , start);
	 			
	  	
			  	if (c.isAffiliate()) {
			  		
			  		output.put("Affiliate", c.isAffiliate());
			  		discount = Float.parseFloat(input.get("amount").toString() ) * AFFILIATE_DISCOUNT;
			  	} 
			  	else if (c.isEmployee()) {
			  		discount = amount * EMPLOYEE_DISCOUNT;
			  		output.put("Employee", c.isEmployee());
			  	}
			  	else if ( Math.abs(years) > 2 ) {
					discount = amount * YEARS_DISCOUNT;
					
					output.put("Number of years as customer", Math.abs( years));
					output.put("dateRegistered", c.dateRegistered);				 	
				 	output.put("Date", LocalDate.now());
					
				}
	  }
			  
	   double netpayment =   amount - discount;
	  	
	 	
	 	output.put("discount", "$ " + discount);
	 	output.put("netpayment", "$ " + netpayment);
	 	
	 	
	 	
	 	
	 	return output;
	}
	
}
