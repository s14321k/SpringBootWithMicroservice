package com.springBATCH.job;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.PlatformTransactionManager;

import com.springBATCH.entity.Customer;
import com.springBATCH.listener.CustomerItemReadListener;
import com.springBATCH.listener.JobCompletionNotificationListener;
import com.springBATCH.processor.CustomerItemProcessor;
import com.springBATCH.repo.CustomerRepo;

//@Configuration
//@EnableBatchProcessing	// Default configuration will be JobRepository, JobRegistory and JobLauncher
public class CsvBatchJob 
{

	public static final Logger log = LoggerFactory.getLogger(CsvBatchJob.class);

	/*
	 * private static final String INSERT_QUERY = """ inset into customer ( id,
	 * firstName, lastName, email, gender, contactNo, country, dob ) values (:id,
	 * :firstName, :lastName, :email, :gender, :contactNo, :country, :dob) """;
	 */

	private final JobRepository jobRepo;
	
	public CsvBatchJob(JobRepository jobRepo) 
	{
		this.jobRepo = jobRepo;
	}
	
	@Autowired
	private CustomerRepo cutomerRepo;

	@Value("classpath:csv/customers.csv")
	private Resource resource;

	
	
	
	
	
	
// -------------------------------------------- Create Reader -> Acts as item reader ------------------------------------------
	
	
	
	
	
	
	
	
	@Bean
	public FlatFileItemReader<Customer> customerReader(LineMapper<Customer> lineMapper) 
	{
		System.out.println("------------------------- Customer Reader ------------------");
		var itemReader = new FlatFileItemReader<Customer>();
//		itemReader.setResource(new FileSystemResource("src/main/resources/csv/customers.csv"));
		itemReader.setResource(resource);
		itemReader.setName("CSV-READER");
		itemReader.setLinesToSkip(1); // This is to skip the 1st row in csv(comma seperated value) excel file
		itemReader.setLineMapper(lineMapper);
		return itemReader;
	}

	@Bean
	public DefaultLineMapper<Customer> defaltLineMap(LineTokenizer tokenizer, FieldSetMapper<Customer> mapper) 
	{
		System.out.println("------------------------- defaltLineMap ------------------");

		var lineMapper = new DefaultLineMapper<Customer>();

		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(mapper);

		return lineMapper;
	}

	@Bean
	public BeanWrapperFieldSetMapper<Customer> fieldSetMapper() 
	{
		System.out.println("------------------------- fieldSetMapper ------------------");
		var filedSetMapper = new BeanWrapperFieldSetMapper<Customer>();
		filedSetMapper.setTargetType(Customer.class);
		return filedSetMapper;
	}

	@Bean
	public DelimitedLineTokenizer tokenizer() 
	{
		System.out.println("------------------------- tokenizer ------------------");
		var lineTokenizer = new DelimitedLineTokenizer(); // gets each and every line from csv
		lineTokenizer.setDelimiter(","); // CSV means comma seperated value. so comma
		lineTokenizer.setStrict(false); // There may be empty data, so it will become null
		lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");
		return lineTokenizer;
	}

	/*
	 * DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(); //gets
	 * each and every line from csv lineTokenizer.setDelimiter(","); //CSV means
	 * comma seperated value. so comma lineTokenizer.setStrict(false); //There may
	 * be empty data, so it will become null lineTokenizer.setNames("id",
	 * "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");
	 * BeanWrapperFieldSetMapper<Customer> filedSetMapper = new
	 * BeanWrapperFieldSetMapper<Customer>();
	 * filedSetMapper.setTargetType(Customer.class);
	 */

	
	
	
	
	
	
	
// --------------------------------------------------- Create Processor -----------------------------------------
	
	
	
	
	
	
	@Bean
	public CustomerItemProcessor customerProcessor() 
	{
		return new CustomerItemProcessor();
	}

	
	
	
	
	
	
	
// ----------------------------------------------------- Create Writer --------------------------------------------
	
	
	
	
	
	
	
	
	/*
	 * @Bean public JdbcBatchItemWriter<Customer> customerWriter(DataSource
	 * dataSource) {
	 * 
	 * var provider = new BeanPropertyItemSqlParameterSourceProvider<Customer>();
	 * var itemWriter = new JdbcBatchItemWriter<Customer>();
	 * itemWriter.setDataSource(dataSource); itemWriter.setSql(INSERT_QUERY);
	 * itemWriter.setItemSqlParameterSourceProvider(provider); return itemWriter; }
	 */
	
	  @Bean 
	  public RepositoryItemWriter<Customer> customerWriter(DataSource dataSource) 
	  {
		  System.out.println("------------------------- customerWriter ------------------");
		  RepositoryItemWriter<Customer> repItemWriter = new RepositoryItemWriter<>();
		  repItemWriter.setRepository(cutomerRepo);
		  repItemWriter.setMethodName("save"); 
		  return repItemWriter;
	  }
	 
	  
	  
	  
	  
	  

// ---------------------------------------------------------- Create Step --------------------------------------------
	  
	  
	  
	  
	  
	  
	  
	/*
	 * //https://stackoverflow.com/questions/40041334/difference-between-step-
	 * tasklet-and-chunk-in-spring-batch
	 * 
	 * @Bean public Tasklet myTasklet() { return new MyTasklet(); }
	 */
	  @Bean
	  public Step stepCustomer(ItemReader<Customer> reader, 
			  			ItemWriter<Customer> writer, 
			  			ItemProcessor<Customer, Customer> processor, 
			  			PlatformTransactionManager platformTransactionManager) 
	  {
		  System.out.println("------------------------- step1 ------------------");
		  // If we want multiple steps, we can create another bean and set the value as `step-2`.
		  var name = "INSERT CSV RECORDS TO DB";
		  var builder = new StepBuilder(name, jobRepo);
		  return builder.<Customer, Customer>chunk(2, platformTransactionManager)
					  .faultTolerant()
					  .retryLimit(3)
					  .retry(PessimisticLockingFailureException.class)
					  .reader(reader)
					  .listener(new CustomerItemReadListener()) // Reader
					  .writer(writer).build();
	}

	  
	  
	  
	  
	  
	  
	  
// ------------------------------------------------------- Create Job -------------------------------------------
	  
	  
	  
	  
	  
	  
	  
	  
	  
	@Bean
	public Job csvJob(@Qualifier("stepCustomer")Step csvJob) 
	{
		System.out.println("------------------------- csvJob ------------------");
		var name = "cvs customer import job";
		var builder = new JobBuilder(name, jobRepo);
		return builder.start(csvJob)
						.listener(new JobCompletionNotificationListener())
						.build();
	}
}
