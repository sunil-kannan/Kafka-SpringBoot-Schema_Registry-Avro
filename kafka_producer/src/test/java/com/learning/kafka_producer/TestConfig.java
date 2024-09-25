package com.learning.kafka_producer;

import com.learning.kafka_producer.service.MessagePublisher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = {MessagePublisher.class})
@DirtiesContext
public class TestConfig {
    @TestConfiguration
    public static class TestEmployeeServiceConfig {
        @Bean
        public MessagePublisher messagePublisher() {
            System.out.println("check");
            return new MessagePublisher();
        }
    }

    @Autowired
    private MessagePublisher eventController;
    @Test
    public void check(){
        System.out.println("OBJECT: "+eventController.hashCode());
    }

}