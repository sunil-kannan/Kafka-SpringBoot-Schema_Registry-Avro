package com.learning.kafka_consumer.config;

import com.learning.kafka_consumer.entity.Student;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class StudentTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;
//    @Value("${consumer.group-id}")
//    private String GROUP_ID;

    @Bean
    public ConsumerFactory<String, Student> defaultKafkaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<String, Student>(
                getConsumerConfig(),
                new StringDeserializer(),
                getStudentErrorHandlingDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Student> kafkaListenerStudentContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Student> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(defaultKafkaConsumerFactory());  // Reference your consumer factory here
        return factory;
    }
    public Map<String, Object> getConsumerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka_student_group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return config;
    }

    private static ErrorHandlingDeserializer<Student> getStudentErrorHandlingDeserializer() {
        JsonDeserializer<Student> deserializer = new JsonDeserializer<>(Student.class);
        deserializer.addTrustedPackages("com.learning.kafka_consumer.entity");
        deserializer.ignoreTypeHeaders();
        return new ErrorHandlingDeserializer<>(deserializer);
    }


}
