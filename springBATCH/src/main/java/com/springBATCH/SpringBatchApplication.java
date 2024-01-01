package com.springBATCH;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication implements CommandLineRunner
{
	
	  private final JobLauncher jobLauncher;
	  
	  @Autowired
	  private final ApplicationContext appContext;
	 
	
	  public SpringBatchApplication(JobLauncher jobLauncher, ApplicationContext appContext) 
	  { 
		  this.jobLauncher = jobLauncher; 
		  this.appContext = appContext; 
	  }
	 

	public static void main(String[] args) 
	{	
		/*
		 * // Bad way to set profiling SpringApplication application = new
		 * SpringApplication(SpringBatchApplication.class);
		 * application.setAdditionalProfiles("prod"); application.run(args);
		 */
		SpringApplication.run(SpringBatchApplication.class, args);
	}
	
	
	public void run(String... args) throws Exception 
	{
//		Job job = (Job) appContext.getBean("csvJob");
		Job job = (Job) appContext.getBean("txtJob");
		JobParameters jobParams = new JobParametersBuilder()
										.addString("JobID", String.valueOf(System.currentTimeMillis()))
										.toJobParameters();
		var jobExecution = jobLauncher.run(job, jobParams);
		var batchStatus = jobExecution.getStatus();
	  
		while(batchStatus.isRunning()) 
		{ 
			System.out.println("Still Running...");
		    Thread.sleep(5000L); 
		 } 
	 }
	 

}
