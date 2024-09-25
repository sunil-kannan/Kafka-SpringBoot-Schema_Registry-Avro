package com.learning.kafka_consumer.service;

import com.learning.kafka_consumer.entity.Student;
import com.learning.kafka_consumer.entity.User;
import com.learning.kafka_producer.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.retrytopic.SameIntervalTopicReuseStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "kafka_topic-2", groupId = "kafka_group-2")
    public void consume1(String msg) throws InterruptedException {
        Thread.sleep(100);
        System.out.println("Consumer 2 Consumed the message: "+msg);
    }

    @KafkaListener(topics = "kafka_topic-3", groupId = "kafka_group-2", containerFactory = "KafkaListenerUserContainerFactory")
    public void consumeUserMessage(@Payload User msg) throws IOException {
        System.out.println(msg);
    }

    @KafkaListener(topics = "student_topic", groupId = "kafka_student_group",
            containerFactory = "kafkaListenerStudentContainerFactory",
            topicPartitions = {@TopicPartition(topic = "student_topic", partitions = {"0"})})
    public void consumeStudentMessageFromPartition0(@Payload Student msg){
        System.out.println("Consumed from Partition 0: "+msg);
    }
    @KafkaListener(topics = "student_topic", groupId = "kafka_student_group",
            containerFactory = "kafkaListenerStudentContainerFactory",
            topicPartitions = {@TopicPartition(topic = "student_topic", partitions = {"1"})})
    public void consumeStudentMessageFromPartition1(@Payload Student msg) {
        System.out.println("Consumed from Partition 1: "+msg);
    }
    @KafkaListener(topics = "student_topic", groupId = "kafka_student_group",
            containerFactory = "kafkaListenerStudentContainerFactory",
            topicPartitions = {@TopicPartition(topic = "student_topic", partitions = {"2"})})
    public void consumeStudentMessageFromPartition2(@Payload Student msg) {
        System.out.println("Consumed from Partition 2: "+msg);
    }


    @RetryableTopic(
            backoff = @Backoff(value = 6000),
            attempts = "4",
            autoCreateTopics = "false",
            retryTopicSuffix = "-retry",
            dltTopicSuffix = "-dlt",
            sameIntervalTopicReuseStrategy = SameIntervalTopicReuseStrategy.SINGLE_TOPIC,
            exclude = {NullPointerException.class}
    )
    @KafkaListener(topics = "topic_employee", groupId = "kafka_group-2", containerFactory = "kafkaListenerEmployeeContainerFactory")
    public void read(ConsumerRecord<String, Employee> consumerRecord, @Headers MessageHeaders headers) {
        log.info("### -> Header acquired: {}", headers);
        Acknowledgment ack = headers.get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        log.info(String.format("#### -> Consumed message -> %s", consumerRecord.value()));
        log.info("#### -> Key: {}", consumerRecord.key());
        log.info("#### -> FirstName: {}", consumerRecord.value().getFirstName());
        if (Objects.nonNull(ack)) ack.acknowledge();
    }

    @DltHandler
    public void dlt(Employee data, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.error("Event from topic {}  is dead lettered - event:{}", topic, data);
    }

}
