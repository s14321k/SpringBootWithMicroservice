package com.springBATCH.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

//@Configuration
//@EnableBatchProcessing	// Default configuration will be JobRepository, JobRegistory and JobLauncher
public class BatchDataSourceConfig 
{
//	DataSource dataSource;
//	
//	public BatchConfig(DataSource dataSource)
//	{
//		this.dataSource = dataSource;
//	}
	
//	@Bean
//	public DataSourceTransactionManager transactionManager()
//	{
//		return new DataSourceTransactionManager();
//	}
//	
//	@Bean
//	public DataSourceInitializer dataSource()
//	{
//		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//		populator.addScript(new ClassPathResource("databse.sql"));
//		populator.setContinueOnError(false);
//		populator.setIgnoreFailedDrops(false);
//		DataSourceInitializer dataInit = new DataSourceInitializer();
//		dataInit.setDataSource(dataSource);
//		dataInit.setDatabasePopulator(populator);
//		return dataInit;
//	}
}
