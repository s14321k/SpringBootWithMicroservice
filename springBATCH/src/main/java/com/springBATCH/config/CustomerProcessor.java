package com.springBATCH.config;

import org.springframework.batch.item.ItemProcessor;

import com.springBATCH.entity.Customer;

public class CustomerProcessor implements ItemProcessor<Customer, Customer>
{

	@Override
	public Customer process(Customer item) throws Exception 
	{
		// Logic to process
//		if(item.getCountry().equals("India"))
//			return item;
		
		return item;
	}

}
