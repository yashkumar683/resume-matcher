package com.job.job_service.DTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {
    
    @JsonProperty("title")
    private String jobTitle;

    @JsonProperty("description")
    private String jobDescription;

    
    @JsonProperty("required_skills")
    private List<String> requiredSkills;
    
    @JsonProperty("posted_by")
    private String postedBy;

}
