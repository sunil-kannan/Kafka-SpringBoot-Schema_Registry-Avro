package com.learning.kafka_producer;


import com.learning.kafka_producer.controller.EventController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Import(KafkaTemplateConfig.class)
//@TestPropertySource(
//		properties = {
//				"spring.kafka.consumer.auto-offset-reset=earliest",
//				"test.topic=kafka_topic-2"
//		}
//)

@TestPropertySource(
		properties = {
				"spring.kafka.consumer.auto-offset-reset=earliest",
//				"spring.datasource.url=jdbc:tc:mysql:8.0.32:///db",
		}
)
@Testcontainers

public class KafkaProducerApplicationTests {

	@Container
	static final KafkaContainer kafka = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:7.4.0")
	);

	@DynamicPropertySource
	static void overrideProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
	}

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;


	@Autowired
	 EventController eventController;

	@Value("${test.topic}")
	private String TEST_TOPIC;

	@Value("${test.topic}")
	private String topic;

	@BeforeAll
	static void startKafka() {
		kafka.start();
	}

	@Test
	public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
			throws Exception {
		String data = "Sending with our own simple KafkaProducer";
		System.out.println(data);
		System.out.println(kafkaTemplate);
		System.out.println(eventController);
		kafkaTemplate.send(topic, "message");
	}
}



