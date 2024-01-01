package com.springBATCH.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.springBATCH.entity.Address;

public class AddressItemProcessor implements ItemProcessor<Address, Address>
{
	
	public static final Logger log = LoggerFactory.getLogger(AddressItemProcessor.class);

	@Override
	public Address process(Address item) throws Exception 
	{
		System.out.println("-------------------------- AddressItemProcessor ---------------------");
		log.info("-------------------------- AddressItemProcessor ---------------------");
		return item;
	}

}
