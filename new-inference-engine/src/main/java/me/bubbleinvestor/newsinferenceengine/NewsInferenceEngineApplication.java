package me.bubbleinvestor.newsinferenceengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "me.bubbleinvestor")
public class NewsInferenceEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsInferenceEngineApplication.class, args);
    }
}
