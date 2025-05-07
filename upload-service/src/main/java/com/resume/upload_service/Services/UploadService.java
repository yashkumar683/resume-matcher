package com.resume.upload_service.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.resume.upload_service.Interfaces.EventPublisher;
import com.resume.upload_service.Interfaces.StorageServcie;
import com.shared.common.DTO.ResumeInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final StorageServcie s3StorageService;
    private final EventPublisher eventPublisher;

    @Value("${resume.bucket}")
    private String bucketName;

    @Value("${parser.topic}")
    private String parserTopic;

    public List<com.shared.common.DTO.ResumeInfo> uploadFile(MultipartFile[] files) {
        try {
            ArrayList<ResumeInfo> resumeInfoList = new ArrayList<>();
           //upload to s3 with generated uuid
            for(MultipartFile file : files) {
                String resumeKey = s3StorageService.uploadFile(file, bucketName);
                ResumeInfo resumeInfo = new ResumeInfo(file.getOriginalFilename(), resumeKey, "Processing");
                resumeInfoList.add(resumeInfo);

                //send to kafka
                eventPublisher.publishEvent(resumeInfo, parserTopic);
            }
            return resumeInfoList; 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
    } 
}
