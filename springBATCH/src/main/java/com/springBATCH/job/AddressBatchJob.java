package com.springBATCH.job;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.PlatformTransactionManager;

import com.springBATCH.entity.Address;
import com.springBATCH.listener.AddressItemReadListener;
import com.springBATCH.listener.JobCompletionNotificationListener;
import com.springBATCH.processor.AddressItemProcessor;
import com.springBATCH.repo.AddressRepo;

@Configuration
public class AddressBatchJob 
{
	public static final Logger log = LoggerFactory.getLogger(AddressBatchJob.class);
	
	private final JobRepository jobRepo;
	
	public AddressBatchJob(JobRepository jobRepo)
	{
		this.jobRepo = jobRepo;
	}
	
	@Autowired
	private AddressRepo addrsRepo;
	
	@Value("classpath:csv/gsdb_namaddr_Pr_11222022.txt")
	private Resource resource;
	
	
	
	
	
	
	
	// -------------------------------------------- Create Reader -> Acts as item reader ------------------------------------------
		
		
		
		
		
		
	@Bean
	public FlatFileItemReader<Address> addrsReader(LineMapper<Address> lineMapper)
	{
		System.out.println("------------------------- Address Reader ------------------");
		var itemReader = new FlatFileItemReader<Address>();
//		itemReader.setResource(new FileSystemResource("src/main/resources/csv/gsdb_namaddr_Pr_11222022.csv"));
		itemReader.setResource(resource);
		itemReader.setName("TXT-READER");
		itemReader.setLinesToSkip(1); // This is to skip the 1st row in csv(comma seperated value) excel file
		itemReader.setLineMapper(lineMapper);
		return itemReader;
	}
	
	@Bean
	public DefaultLineMapper<Address> defaltLineMapAddrs(LineTokenizer tokenizer, FieldSetMapper<Address> mapper) 
	{
		System.out.println("------------------------- address defaltLineMap ------------------");

		var lineMapper = new DefaultLineMapper<Address>();

		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(mapper);

		return lineMapper;
	}
	
	
	@Bean
	public BeanWrapperFieldSetMapper<Address> fieldSetMapperAddrs() 
	{
		System.out.println("------------------------- address fieldSetMapper ------------------");
		var filedSetMapper = new BeanWrapperFieldSetMapper<Address>();
		filedSetMapper.setTargetType(Address.class);
		return filedSetMapper;
	}

	@Bean
	public FixedLengthTokenizer tokenizerAddrs() 
	{
		System.out.println("------------------------- address tokenizer ------------------");
		var lineTokenizer = new FixedLengthTokenizer(); // gets each and every line from csv
		lineTokenizer.setColumns(new Range(1, 46), new Range(47, 108));
		lineTokenizer.setStrict(false); // There may be empty data, so it will become null
		lineTokenizer.setNames("column1","column2");
		return lineTokenizer;
	}
	
	
	
	
	
	
	
	
	// --------------------------------------------------- Create Processor -----------------------------------------
		
		
		
		
		
	@Bean
	public AddressItemProcessor addressProcessor()
	{
		return new AddressItemProcessor();
	}
	

	
	
	
	
	
	
	
	// ----------------------------------------------------- Create Writer --------------------------------------------
		
		
		
		
		
		
		
	@Bean 
	public RepositoryItemWriter<Address> addressWriter(DataSource dataSource) 
	{
		System.out.println("------------------------- Address Writer ------------------");
		RepositoryItemWriter<Address> repItemWriter = new RepositoryItemWriter<>();
		repItemWriter.setRepository(addrsRepo);
		repItemWriter.setMethodName("save"); 
		return repItemWriter;
	  }
	
	// ---------------------------------------------------------- Create Step --------------------------------------------
	  
	  
	  
	  
	  
	  
	  
	
	@Bean
	public Step stepAddrs(ItemReader<Address> reader, 
		  			ItemWriter<Address> writer, 
		  			ItemProcessor<Address, Address> processor, 
		  			PlatformTransactionManager platformTransactionManager) 
	{
		System.out.println("------------------------- address step1 ------------------");
		// If we want multiple steps, we can create another bean and set the value as `step-2`.
		var name = "INSERT TXT RECORDS TO DB";
		var builder = new StepBuilder(name, jobRepo);
		return builder.<Address, Address>chunk(10, platformTransactionManager)
					  .faultTolerant()
//					  .retryLimit(3)
//					  .retry(PessimisticLockingFailureException.class)
					  .reader(reader)
					  .listener(new AddressItemReadListener()) // Reader
					  .writer(writer)
					  .build();
	}

		  
	 
	// ------------------------------------------------------- Create Job -------------------------------------------
		  
		  
		  
		  
		  
		  
		  
		  
		  
	@Bean
	@Primary
	public Job txtJob(@Qualifier("stepAddrs")Step txtJob) 
	{
		System.out.println("------------------------- txtJob ------------------");
		var name = "TXT  address import job";
		var builder = new JobBuilder(name, jobRepo);
		return builder.start(txtJob)
						.listener(new JobCompletionNotificationListener())
						.build();
	}
		  
}
