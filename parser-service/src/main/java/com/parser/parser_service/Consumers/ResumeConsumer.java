package com.parser.parser_service.Consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.parser.parser_service.Services.ResumeHandlerService;
import com.shared.common.DTO.ResumeInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeConsumer {
    //Add handler Service

    private final ResumeHandlerService resumeHandlerService;

    @KafkaListener(topics = "${parser.topic}", groupId = "parser-group")
    public void resumeConsume(@Payload ResumeInfo resumeInfo, Acknowledgment ack) {
        log.info("Consume--{}", resumeInfo);
        resumeHandlerService.handle(resumeInfo);
    }
}
