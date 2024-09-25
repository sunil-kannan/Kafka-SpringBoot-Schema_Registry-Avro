package com.learning.kafka_producer.service;

import com.learning.kafka_producer.dto.Employee;
import com.learning.kafka_producer.entity.Student;
import com.learning.kafka_producer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
//@RequiredArgsConstructor
public class MessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplateForObject;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplateForUser;

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplateForStudent;

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplateForEmployee;

    @Value("${test.topic}")
    private String KAFKA_TOPIC;

    @Value("${kafka.topic.student}")
    private String TOPIC_STUDENT;
    public void sendStringToTopic(String message) throws ExecutionException, InterruptedException {
        for(int i=0 ; i<10; i++){
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplateForObject.send(KAFKA_TOPIC, message +" : "+ i);
        }
    }

    public void sendUserToTopic(User user){
        CompletableFuture<SendResult<String, User>> future = kafkaTemplateForUser.send("kafka_topic-3", user );
    }

    public void sendStudentTopic(Student student){
        // sending messages to only partition 3
        CompletableFuture<SendResult<String,Student>> future = kafkaTemplateForStudent.send(TOPIC_STUDENT,0, null, student);
        CompletableFuture<SendResult<String,Student>> future1 = kafkaTemplateForStudent.send(TOPIC_STUDENT,1, null, student);
        CompletableFuture<SendResult<String,Student>> future2 = kafkaTemplateForStudent.send(TOPIC_STUDENT,2, null, student);
    }

    public void sendThroughArvo(Employee employee){
        CompletableFuture<SendResult<String, Employee>> future = kafkaTemplateForEmployee.send("topic_employee", UUID.randomUUID().toString(),employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + employee +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        employee + "] due to : " + ex.getMessage());
            }
        });
    }


}
