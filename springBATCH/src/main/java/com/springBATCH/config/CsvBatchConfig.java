package com.springBATCH.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.springBATCH.entity.Customer;
import com.springBATCH.repo.CustomerRepo;

@Configuration
@EnableBatchProcessing
public class CsvBatchConfig 
{
	@Autowired
	private CustomerRepo customRep;
	
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
	@Bean
	public CustomerProcessor customerProcessor()
	{
		return new CustomerProcessor();
	}
	
// Create Writer
	@Bean
	public RepositoryItemWriter<Customer> customerWriter()
	{
		RepositoryItemWriter<Customer> repItemWriter = new RepositoryItemWriter<>();
		repItemWriter.setRepository(customRep);
		repItemWriter.setMethodName("save");
		return repItemWriter;
	}
	
	
// Create Step
	//https://stackoverflow.com/questions/40041334/difference-between-step-tasklet-and-chunk-in-spring-batch
	/*
	 * @Bean public Tasklet myTasklet() { return new MyTasklet(); }
	 */
	
	
	// Create Job

}
