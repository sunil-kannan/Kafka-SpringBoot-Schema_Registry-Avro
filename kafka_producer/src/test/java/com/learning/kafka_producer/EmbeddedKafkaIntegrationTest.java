//package com.learning.kafka_impl;
//
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.stereotype.Component;
//import org.springframework.test.annotation.DirtiesContext;
//
//@Configuration
//@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
//public class EmbeddedKafkaIntegrationTest {
//
//    @Autowired
//    private KafkaConsumer consumer;
//
//    @Autowired
//    private KafkaProducer producer;
//
//    @Value("${test.topic}")
//    private String topic;
//
//    @Test
//    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
//            throws Exception {
//        String data = "Sending with our own simple KafkaProducer";
//
//        producer.send(topic, data);
//
//        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
//        assertTrue(messageConsumed);
//        assertThat(consumer.getPayload(), containsString(data));
//    }
//}