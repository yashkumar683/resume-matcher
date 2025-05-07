package com.parser.parser_service.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.parser.parser_service.DTO.MatchingResult;
import com.parser.parser_service.DTO.ParsedResume;
import com.parser.parser_service.Interfaces.MatchInterface;
import com.parser.parser_service.Interfaces.ResumeParser;
import com.parser.parser_service.Interfaces.StorageService;
import com.parser.parser_service.Models.ResumeMatchResult;
import com.parser.parser_service.Repository.ResumeMatchResultRepository;
import com.shared.common.DTO.ResumeInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeHandlerService {

    private final StorageService storageService;

    private final ResumeParser resumeParser;

    private final MatchInterface matchService;

    private final ResumeMatchResultRepository resumeMatchResultRepository;

    @Value("${resume.bucket}")
    private String bucketName;

    public void handle(ResumeInfo resumeInfo) {
        //call s3 service to get resume from s3.
        log.info("Resume info", resumeInfo);
        byte[] resume = storageService.getFile(resumeInfo.getResumeKey(), bucketName);

        //parse resume to get keywords
        ParsedResume parsed = resumeParser.parse(resume, resumeInfo.getFileName());

        //check matching keywords against JD in mongoDB.
        MatchingResult matched = matchService.findBestMatch(parsed.getSkills());
        log.info("Result--" + matched);

        //store it in status collection of mongoDB.
        ResumeMatchResult result = ResumeMatchResult.builder()
                                            .resumeKey(resumeInfo.getResumeKey())
                                            .email(parsed.getEmail())
                                            .phone(parsed.getMobile())
                                            .parsedSkills(parsed.getSkills())
                                            .matchedJob(matched)
                                            .build();

        resumeMatchResultRepository.save(result);
    }

    public ResumeMatchResult getResumeResult(String resumeKey) {
        return resumeMatchResultRepository.findByResumeKey(resumeKey);
    
    }
 }
