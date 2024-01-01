package com.springBATCH.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.springBATCH.entity.Customer;

public class CustomerItemReadListener implements ItemReadListener<Customer>
{
	public static final Logger log = LoggerFactory.getLogger(CustomerItemReadListener.class);
	
	public void beforeRead()
	{
		log.info("CustomerItemReadListener ---------------  Read a new Person Record-----------------------");
	}
	
	public void afterRead(Customer input)
	{
		log.info("CustomerItemReadListener ---------------  New Customer record read : " + input+"  --------------------");
	}
	
	public void onReadError(Exception e)
	{
		log.error("CustomerItemReadListener --------------  Error in reading the person record : " + e+ "  --------------------");
	}
}
