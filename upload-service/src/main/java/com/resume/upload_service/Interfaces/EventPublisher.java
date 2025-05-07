package com.resume.upload_service.Interfaces;

public interface EventPublisher {
    public <T> void publishEvent(T event, String topic);
}
