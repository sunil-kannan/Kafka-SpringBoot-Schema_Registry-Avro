package com.learning.kafka_producer.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private String department;
    private Integer age;
}
