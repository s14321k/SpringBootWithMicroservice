package com.springBATCH.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCompletionNotificationListener implements JobExecutionListener
{
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	@Override
	public void afterJob(JobExecution jobExcecution)
	{
		if(jobExcecution.getStatus() == BatchStatus.COMPLETED)
			log.info("JOB-FINISHED !!");
	}
}
