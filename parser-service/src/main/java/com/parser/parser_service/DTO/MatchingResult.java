package com.parser.parser_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MatchingResult {
    private String jobId;
    private String jobTitle;
    private double matchScore;
}
