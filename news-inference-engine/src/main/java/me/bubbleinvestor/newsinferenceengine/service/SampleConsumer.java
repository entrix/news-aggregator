package me.bubbleinvestor.newsinferenceengine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Service
public class SampleConsumer {
    private static final Logger log = LoggerFactory.getLogger(SampleConsumer.class.getName());
    private final SimpleDateFormat dateFormat;

    @Autowired
    private KafkaReceiver kafkaReceiver;

    public SampleConsumer() {
        this.dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
    }

    public Disposable consumeMessages(CountDownLatch latch) {
        Flux<ReceiverRecord<Integer, String>> kafkaFlux = kafkaReceiver.receive();
        return kafkaFlux.subscribe(record -> {
            ReceiverOffset offset = record.receiverOffset();
            System.out.printf("Received message: topic-partition=%s offset=%d timestamp=%s key=%d value=%s\n",
                    offset.topicPartition(),
                    offset.offset(),
                    dateFormat.format(new Date(record.timestamp())),
                    record.key(),
                    record.value());
            offset.acknowledge();
            latch.countDown();
        });


    }
}
