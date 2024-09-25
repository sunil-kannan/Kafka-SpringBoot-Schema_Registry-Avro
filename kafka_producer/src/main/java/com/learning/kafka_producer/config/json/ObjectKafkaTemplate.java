package com.learning.kafka_producer.config.json;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ObjectKafkaTemplate {
    @Bean
    public ProducerFactory<String, Object> objectProducerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.learning.kafka_impl.entity");
        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean(name = "kafkaTemplateForObject")
    public KafkaTemplate<String, Object> kafkaTemplateForObject() {
        return new KafkaTemplate<>(objectProducerFactory());
    }
}
