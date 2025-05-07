package com.job.job_service.Services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.job.job_service.DTOs.JobRequest;
import com.job.job_service.DTOs.StatusRequest;
import com.job.job_service.Models.JobDescription;
import com.job.job_service.Repositories.JobDescriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobDescriptionRepository jobDescriptionRepository;

    @Value("${job.old.period}")
    private int oldTime;
    
    public String createJob(JobRequest jobRequest) {
        
        JobDescription jd = new JobDescription();
        jd.setJobTitle(jobRequest.getJobTitle());
        jd.setJobDescription(jobRequest.getJobDescription());
        jd.setPostedBy(jobRequest.getPostedBy());
        jd.setRequiredSkills(jobRequest.getRequiredSkills());
        
        jobDescriptionRepository.save(jd);
        return "Created Successfully";
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteOldJobDescription() {
        LocalDateTime time = LocalDateTime.now().minus(oldTime, ChronoUnit.DAYS);

        long deletedCount = jobDescriptionRepository.deleteByCreatedAtBefore(time);
        System.out.println("Deleted " + deletedCount + " old job descriptions.");
    }

    public String updateJobStatus(StatusRequest statusRequest) {
        JobDescription jd = jobDescriptionRepository.findById(statusRequest.getJobID())
                                .orElseThrow(()-> new RuntimeException("Job Not found!"));

        jd.setJobStatus(statusRequest.getStatus());
        jobDescriptionRepository.save(jd);

        return "Status Updated!";
    }
}
