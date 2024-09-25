package com.learning.kafka_consumer.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Library {
    private String id;
    private String name;
}
