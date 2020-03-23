package com.brinkmcd.udemy.niyazi.rabbitmqdemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqdemoApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqdemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		SimpleMessage simpleMessage = new SimpleMessage();
		simpleMessage.setName("CoolMessage");
		simpleMessage.setDescription("This is a cool message.");
		
		// rabit template allows us to convertAndSend pretty much any data type (file, String, Object, etc.)
		// params: <exchange name>, <routing key>, message
		rabbitTemplate.convertAndSend("MyTopicExchange", "topic", simpleMessage.toString());
		
	}

}
