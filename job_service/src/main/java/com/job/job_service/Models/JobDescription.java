package com.job.job_service.Models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.job.job_service.Enums.JobStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "job_description")
public class JobDescription {
    
    @Id
    @JsonProperty("job_id")
    private String jobID;

    @JsonProperty("job_title")
    private String jobTitle;

    
    @JsonProperty("job_description")
    private String jobDescription;

    
    @JsonProperty("required_skills")
    private List<String> requiredSkills;

    
    @JsonProperty("posted_by")
    private String postedBy;

    @JsonProperty("status")
    private JobStatus jobStatus = JobStatus.ACTIVE;

    @CreatedDate
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

}
