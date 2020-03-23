package com.brinkmcd.udemy.niyazi.rabbitmq.listener.rabbitListener;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfig {

	// Exchange is an interface, so we must instantiate an implementation
	// such as TopicExchange rather than Exchange directly
	
	@Bean
	Exchange exampleExchange() {
		return new TopicExchange("ExampleTopicExchange");
	}
	
	@Bean
	Exchange example2Exchange() {
		return ExchangeBuilder.directExchange("Example2Exchange")
				.autoDelete()
				.internal()
				.build();
	}
	
	@Bean
	Exchange newExchange() {
		return ExchangeBuilder.topicExchange("TopicTestExchange")
				.durable(true)
				.internal()
				.build();
	}
	
	@Bean
	Exchange fanoutExchange() {
		return ExchangeBuilder.fanoutExchange("BigFanExchange")
				.autoDelete()
				.build();
	}
	
	@Bean
	Exchange headersExchange() {
		return ExchangeBuilder.headersExchange("HeadersTestExchange")
				.autoDelete()
				.internal()
				.durable(true)
				.ignoreDeclarationExceptions()
				.build();
	}
	
}
