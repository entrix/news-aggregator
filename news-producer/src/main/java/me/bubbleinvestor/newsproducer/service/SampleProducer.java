package me.bubbleinvestor.newsproducer.service;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

@Service
public class SampleProducer {

    private static final Logger log = LoggerFactory.getLogger(SampleProducer.class.getName());
    private final SimpleDateFormat dateFormat;
    @Autowired
    private KafkaSender sender;

    public SampleProducer() {
        this.dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
    }

    public void sendMessages(String topic, int count, CountDownLatch latch) throws InterruptedException {
        sender.<Integer>send(Flux.range(1, count)
                .map(i -> SenderRecord.create(new ProducerRecord<>(topic, i, "Message_" + i), i)))
                .doOnError(e -> log.error("Send failed", e))
                .subscribe(r -> System.out.println("Received: " + r));
    }

    public void close() {
        sender.close();
    }

}
