package com.job.job_service.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.job.job_service.Enums.JobStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest {
    
    @JsonProperty("job_id")
    private String jobID;

    private JobStatus status;
}
