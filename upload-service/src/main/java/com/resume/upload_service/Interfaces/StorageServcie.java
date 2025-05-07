package com.resume.upload_service.Interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageServcie {
    public String uploadFile(MultipartFile files, String bucketName) throws IOException;
}
