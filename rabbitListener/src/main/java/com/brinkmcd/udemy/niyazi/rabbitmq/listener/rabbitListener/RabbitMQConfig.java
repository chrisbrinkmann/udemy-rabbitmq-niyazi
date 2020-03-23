package com.brinkmcd.udemy.niyazi.rabbitmq.listener.rabbitListener;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	private static final String MY_QUEUE = "MyQueue";
	
	// define/create(via the Bean) the queue
	@Bean
	Queue getQueue() {
		return new Queue(MY_QUEUE, true);
	}
	
	
	@Bean
	Exchange getExchange() {
		return ExchangeBuilder.topicExchange("MyTopicExchange")
				.durable(true)
				.build();
	}
	
	@Bean
	Binding getBinding() {
		// Build a binding using new
		// params: Queue, DestinationType, <exchange name>, <routing key>, args
//		return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE, "MyTopicExchange", "topic", null);
		
		// Build a binding using BindingBuilder
		return BindingBuilder
				.bind(getQueue()) 
				.to(getExchange())
				.with("topic") // routing key
				.noargs();		
	}
	
	// Create a connection to the RabbitMQ broker
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setPassword("guest");
		return cachingConnectionFactory;
	}
	
	// Bind the Queue, connection, and message listener
	@Bean
	MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(getQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
		return simpleMessageListenerContainer;
	}
}
