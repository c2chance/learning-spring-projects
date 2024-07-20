package com.springframework.amqp.tutorials;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring profiles to control its behavior.\n");
			System.out.println("Options are: ");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=hello-world,receiver");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=hello-world,sender");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=work-queues,receiver");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=work-queues,sender");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=pub-sub,receiver --tutorial.client.duration=60000");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=pub-sub,sender --tutorial.client.duration=60000");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=routing,receiver");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=routing,sender");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=topics,receiver");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=topics,sender");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=rpc,client");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=rpc,server");
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitAmqpTutorialsRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
	}

}
