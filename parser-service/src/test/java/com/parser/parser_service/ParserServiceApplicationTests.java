package com.parser.parser_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
	"spring.kafka.bootstrap-servers=localhost:9092",
	"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration"
  })  
class ParserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
