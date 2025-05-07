package com.parser.parser_service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parser.parser_service.Models.ResumeMatchResult;

public interface ResumeMatchResultRepository extends MongoRepository<ResumeMatchResult, String> {
    ResumeMatchResult findByResumeKey(String resumeKey);
}
