package com.learning.kafka_consumer.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private int id;
    private String name;
    private Library library;
}
