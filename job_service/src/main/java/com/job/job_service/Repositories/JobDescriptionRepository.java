package com.job.job_service.Repositories;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.job.job_service.Models.JobDescription;

public interface JobDescriptionRepository extends MongoRepository<JobDescription, String> {
    long deleteByCreatedAtBefore(LocalDateTime date);
}
