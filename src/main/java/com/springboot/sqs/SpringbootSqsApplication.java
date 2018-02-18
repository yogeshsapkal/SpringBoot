package com.springboot.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.sqs.service.SQSService;

@SpringBootApplication
public class SpringbootSqsApplication implements CommandLineRunner
{
	@Autowired
	SQSService sqsReaderService;

	public static void main(String[] args)
	{
		SpringApplication.run(SpringbootSqsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception
	{
		sqsReaderService.sendMessage();
		sqsReaderService.readMessage();

	}
}
