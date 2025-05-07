package com.parser.parser_service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parser.parser_service.Models.JobDescription;

public interface JobDescriptionRepository extends MongoRepository<JobDescription, String> {
    
}