package com.learning.kafka_consumer.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String department;
    private Integer age;
}
