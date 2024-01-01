package com.springBATCH.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.springBATCH.entity.Address;

public class AddressItemReadListener implements ItemReadListener<Address> 
{
public static final Logger log = LoggerFactory.getLogger(AddressItemReadListener.class);
	
	public void beforeRead()
	{
		log.info("AddressItemReadListener ---------------  Read a new Record-----------------------");
	}
	
	public void afterRead(Address input)
	{
		log.info("AddressItemReadListener ---------------  New record read : " + input+"  --------------------");
	}
	
	public void onReadError(Exception e)
	{
		log.error("AddressItemReadListener --------------  Error in reading the record : " + e+ "  --------------------");
	}

}
