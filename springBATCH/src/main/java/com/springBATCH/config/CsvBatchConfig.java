package com.springBATCH.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.springBATCH.entity.Customer;

@Configuration
@EnableBatchProcessing
public class CsvBatchConfig 
{
	// Create Reader -> Acts as item reader
	@Bean
	public FlatFileItemReader<Customer> customerReader()
	{
		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<Customer>();
		itemReader.setResource(new FileSystemResource("src/main/resources/customer.csv"));
		itemReader.setName("CSV-READER");
		itemReader.setLinesToSkip(1); // This is to skip the 1st row in csv(comma seperated value) excel file
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Customer> lineMapper() 
	{
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<Customer>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(); //gets each and every line from csv
		lineTokenizer.setDelimiter(",");	//CSV means comma seperated value. so comma
		lineTokenizer.setStrict(false);		//There may be empty data, so it will become null
		lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");
		
		BeanWrapperFieldSetMapper<Customer> filedSetMapper = new BeanWrapperFieldSetMapper<Customer>();
		filedSetMapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(filedSetMapper);
		
 		return lineMapper;
	}
	
	// Create Processor
	// Create Writer
	// Create Step
	// Create Job

}
