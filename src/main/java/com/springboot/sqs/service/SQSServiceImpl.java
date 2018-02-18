package com.springboot.sqs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

@Service
public class SQSServiceImpl implements SQSService
{

	@Value("${queue.queueName}")
	private String queueName;

	@Autowired
	private AmazonSQS amazonSQSClient;

	@Override
	public List<Message> readMessage()
	{
		ReceiveMessageResult receiveMessageResult = null;
		List<Message> messagesList = null;
		try
		{
			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueName);
			receiveMessageResult = amazonSQSClient.receiveMessage(receiveMessageRequest);
			messagesList = receiveMessageResult.getMessages();
			System.out.println("Number of messages from sqs queue" + messagesList.size());
			for (Message message : messagesList)
			{
				System.out.println("message " + message.getBody() + "::" + message.getReceiptHandle());
				deleteMessage(message.getReceiptHandle());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return messagesList;
	}

	@Override
	public boolean sendMessage()
	{
		boolean messageSend = false;
		try
		{
			amazonSQSClient.sendMessage(queueName, "first Message");
			System.out.println("message Send");
			messageSend = true;
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return messageSend;
	}

	@Override
	public boolean deleteMessage(String receiptHandle)
	{
		amazonSQSClient.deleteMessage(queueName, receiptHandle);
		return false;
	}

}
