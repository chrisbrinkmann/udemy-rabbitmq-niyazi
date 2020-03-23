package com.brinkmcd.udemy.niyazi.rabbitmq.listener.rabbitListener;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfig {

	// There are different methods for creating queues:
	
	// 1. using the Queue object directly in a Spring Bean
	@Bean
	Queue exampleQueue() {
		return new Queue("ExampleQueue", false);
	}
	
	// 2. using the QueueBuilder to build a Queue in a Spring bean
//	@Bean
//	Queue example2Queue() {
//		return QueueBuilder.durable("Example2Queue")
//				.autoDelete()
//				.build();
//	}
	
	// Running our application with above code will create the queues,
	// but since we did not bind the queues to the message listener class
	// the listener is not listening to them and the onMessage will not be triggered
}
