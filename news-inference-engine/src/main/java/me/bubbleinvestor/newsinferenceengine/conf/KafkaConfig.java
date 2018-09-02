package me.bubbleinvestor.newsinferenceengine.conf;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class.getName());


    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;
    @Value("${kafka.group.id}")
    private String groupId;
    @Value("${kafka.topic.name}")
    private String topic;


    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "news-inference-engine");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    @Bean
    public ReceiverOptions<Object, Object> receiverOptions() {
        return ReceiverOptions.create(consumerConfigs()).subscription(Collections.singleton(topic))
                    .addAssignListener(partitions -> log.debug("onPartitionsAssigned {}", partitions))
                    .addRevokeListener(partitions -> log.debug("onPartitionsRevoked {}", partitions));
    }

    @Bean
    public KafkaReceiver kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }
}
