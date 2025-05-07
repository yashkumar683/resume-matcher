package com.parser.parser_service.Services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.parser.parser_service.Interfaces.StorageService;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Service
@RequiredArgsConstructor
public class S3StorageService implements StorageService {

    private final S3Client s3Client;

    public byte[] getFile(String filename, String bucket) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                            .bucket(bucket)
                                            .key(filename)
                                            .build();
                                
        try {
            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest);
            byte[] res = s3Object.readAllBytes();
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
