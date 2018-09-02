package me.bubbleinvestor.newsproducer.controller;

import me.bubbleinvestor.newsproducer.service.SampleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
public class Index {

    @Value("${kafka.topic.name}")
    private String topic;

    @Autowired
    SampleProducer producer;

    @RequestMapping("/")
    public String index() throws InterruptedException {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        producer.sendMessages(topic, count, latch);
        latch.await(10, TimeUnit.SECONDS);
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/stop")
    public String stop() {
        producer.close();
        return "Stopped"    ;
    }
}
