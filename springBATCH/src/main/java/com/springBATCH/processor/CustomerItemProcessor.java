package com.springBATCH.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.springBATCH.entity.Customer;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer>
{
	
	public static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

	@Override
	public Customer process(Customer item) throws Exception 
	{
		// Logic to process
//		if(item.getCountry().equals("India"))
//			return item;
		
		System.out.println("-------------------------- CustomerItemProcessor ---------------------");
		log.info("-------------------------- CustomerItemProcessor ---------------------");
		
		return item;
	}

}
