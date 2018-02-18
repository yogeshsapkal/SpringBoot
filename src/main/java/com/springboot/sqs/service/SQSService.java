package com.springboot.sqs.service;

import java.util.List;

import com.amazonaws.services.sqs.model.Message;

public interface SQSService
{
	public List<Message> readMessage();
	public boolean sendMessage();
	public boolean deleteMessage(String receiptHandle);
}
