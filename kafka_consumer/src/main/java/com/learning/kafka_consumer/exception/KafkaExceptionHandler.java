//package com.learning.kafka_consumer.exception;
//
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.listener.CommonErrorHandler;
//import org.springframework.kafka.listener.MessageListenerContainer;
//
//public class KafkaExceptionHandler implements CommonErrorHandler {
////    @Override
////    public void handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
////        handle(exception, consumer);
////    }
//
//    @Override
//    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
//        handle(exception, consumer);
//    }
//
//    private void handle(Exception exception, Consumer<?, ?> consumer) {
//        System.err.println(exception.getMessage());
//
//    }
//}
