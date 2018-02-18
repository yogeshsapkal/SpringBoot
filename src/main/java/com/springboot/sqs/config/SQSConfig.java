package com.springboot.sqs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
public class SQSConfig
{
	@Bean
	public AmazonSQS instantiateAmazonSQSClient()
	{
		return AmazonSQSClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new DefaultAWSCredentialsProviderChain()).build();
	}
}
