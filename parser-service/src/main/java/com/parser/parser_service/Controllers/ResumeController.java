package com.parser.parser_service.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.parser.parser_service.DTO.ResumeStatusDTO;
import com.parser.parser_service.Models.ResumeMatchResult;
import com.parser.parser_service.Services.ResumeHandlerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ResumeController {

    private ResumeHandlerService resumeHandlerService;
    
    @GetMapping("resume/status/{resume_key}")
    public ResponseEntity<ResumeStatusDTO> getResumeStatus(@PathVariable("resume_key") String resumeKey) {
       ResumeMatchResult result = resumeHandlerService.getResumeResult(resumeKey);

       if(result == null) return ResponseEntity.notFound().build();

       return ResponseEntity.ok(resumeStatusMapper(result));

    }

    private ResumeStatusDTO resumeStatusMapper(ResumeMatchResult result) {
        return ResumeStatusDTO.builder()
                    .resumeKey(result.getResumeKey())
                    .email(result.getEmail())
                    .phone(result.getPhone())
                    .parsedSkills(result.getParsedSkills())
                    .matchedJob(result.getMatchedJob())
                    .status(result.getStatus())
                    .build();
    }
}
