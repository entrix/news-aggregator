package me.bubbleinvestor.newsinferenceengine.controller;

import me.bubbleinvestor.newsinferenceengine.service.SampleConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
public class Index {

    @Value("${kafka.topic.name}")
    private String topic;

    @Autowired
    SampleConsumer consumer;

    @RequestMapping("/")
    public String index() throws InterruptedException {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        Disposable disposable = consumer.consumeMessages(topic, latch);
        latch.await(10, TimeUnit.SECONDS);
        disposable.dispose();
        return "hi from newsinferenceengine";
    }


}