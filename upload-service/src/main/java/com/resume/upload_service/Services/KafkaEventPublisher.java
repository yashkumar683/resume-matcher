package com.resume.upload_service.Services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.resume.upload_service.Interfaces.EventPublisher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    public <T> void publishEvent(T message, String topic) {
        kafkaTemplate.send(topic, message);
    }
}
