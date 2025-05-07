package com.parser.parser_service.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ResumeStatusDTO {

    @JsonProperty("resume_key")
    private String resumeKey;

    private String email;

    private String phone;

    @JsonProperty("parsed_skills")
    private List<String> parsedSkills;

    @JsonProperty("matched_job")
    private MatchingResult matchedJob;

    private String status;
}
