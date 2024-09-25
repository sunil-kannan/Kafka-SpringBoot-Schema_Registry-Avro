package com.learning.kafka_producer.controller;

import com.learning.kafka_producer.dto.Employee;
import com.learning.kafka_producer.entity.Library;
import com.learning.kafka_producer.entity.Student;
import com.learning.kafka_producer.entity.User;
import com.learning.kafka_producer.service.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("producer/publish")
public class EventController {

    private final MessagePublisher publisher;

    @GetMapping("String/{msg}")
    public ResponseEntity<?> publishStringMessage(@PathVariable("msg") String msg) throws ExecutionException, InterruptedException {
        try{
            publisher.sendStringToTopic(msg);
            return ResponseEntity.ok("Message published successfully..");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("user")
    public ResponseEntity<?> publishUserMessage(@RequestBody User user){
        try{
            publisher.sendUserToTopic(user);
            return ResponseEntity.ok("Message published successfully..");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("student")
    public ResponseEntity<?> publishStudent(@RequestBody Student student) throws ExecutionException, InterruptedException {
        try{
            publisher.sendStudentTopic(student);
            return ResponseEntity.ok("Message published successfully..");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/employee")
    public String sendMessageThroughArvo(@RequestBody Employee employee) {
        publisher.sendThroughArvo(employee);
        return "message published !";
    }


}
