package com.learning.kafka_producer.config;
import com.learning.kafka_producer.entity.User;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.student}")
    private String TOPIC_STUDENT;


    @Bean
    public NewTopic createTopic3(){
        return new NewTopic("kafka_topic-3",2, (short) 1);
    }
    @Bean
    public NewTopic createStudentTopic(){
        return new NewTopic(TOPIC_STUDENT,3, (short) 1);
    }
    @Bean
    public NewTopic createEmployeeTopic(){
        return new NewTopic("topic_employee",3, (short) 1);
    }





}
