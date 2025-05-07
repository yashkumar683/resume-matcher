package com.parser.parser_service.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.parser.parser_service.DTO.MatchingResult;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "resume_match_results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeMatchResult {

    @Id
    private String id;

    private String resumeKey;

    private String email;

    private String phone;

    private List<String> parsedSkills;

    private MatchingResult matchedJob;

    private String status;

    private LocalDateTime createdAt;
}

