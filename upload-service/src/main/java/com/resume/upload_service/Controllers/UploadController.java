package com.resume.upload_service.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resume.upload_service.DTO.ApiResponse;
import com.resume.upload_service.Services.UploadService;
import com.shared.common.DTO.ResumeInfo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @PostMapping("/upload")
    public ApiResponse<List<ResumeInfo>> upload(@RequestParam("files") MultipartFile[] files) {
        List<ResumeInfo> resumekeys = uploadService.uploadFile(files);
        return new ApiResponse<List<ResumeInfo>>("Success", resumekeys);
    }

}
