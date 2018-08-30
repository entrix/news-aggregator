package me.bubbleinvestor.newsproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "me.bubbleinvestor")
public class NewsProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsProducerApplication.class, args);
	}
}
