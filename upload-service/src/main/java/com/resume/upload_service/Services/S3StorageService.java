package com.resume.upload_service.Services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.resume.upload_service.Interfaces.StorageServcie;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3StorageService implements StorageServcie {

    private final S3Client s3Client;

    public String uploadFile(MultipartFile files, String bucketName) throws IOException {

        try {

            String s3Key = UUID.randomUUID() + "-" + files.getOriginalFilename();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                                    .bucket(bucketName)
                                                    .key(s3Key)
                                                    .contentType(files.getContentType())
                                                    .build();
 
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(files.getBytes()));
            return s3Key;
            
        } catch (IOException e) {
            throw new IOException("Failed to upload to s3");
        }
    }
}
