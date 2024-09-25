package com.learning.kafka_producer.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Library {
    private Integer id;
    private String name;
}
