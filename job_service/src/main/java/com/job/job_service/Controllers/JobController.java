package com.job.job_service.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.job_service.DTOs.JobRequest;
import com.job.job_service.DTOs.StatusRequest;
import com.job.job_service.Services.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<String> createJob(@RequestBody JobRequest jobRequest) {
        String message = jobService.createJob(jobRequest);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateJobStatus(@RequestBody StatusRequest statusRequest) {
        String message = jobService.updateJobStatus(statusRequest);
        return ResponseEntity.ok(message);
    }
    
}
