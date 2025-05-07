package com.parser.parser_service.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.parser.parser_service.DTO.MatchingResult;
import com.parser.parser_service.Interfaces.MatchInterface;
import com.parser.parser_service.Models.JobDescription;
import com.parser.parser_service.Repository.JobDescriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingService implements MatchInterface {

    private final JobDescriptionRepository jobDescriptionRepository;

    public MatchingResult findBestMatch(List<String> resumeSkills) {
        if (resumeSkills == null || resumeSkills.isEmpty()) {
            return null; // or throw an exception if required
        }

        Set<String> normalizedResumeSkills = resumeSkills.stream()
                .map(this::normalize)
                .collect(java.util.stream.Collectors.toSet());

        List<JobDescription> allJDs = jobDescriptionRepository.findAll();

        String bestJobId = null;
        String bestJobTitle = null;
        double bestScore = 0;

        for (JobDescription jd : allJDs) {
            List<String> jdSkills = jd.getRequiredSkills();

            if (jdSkills == null || jdSkills.isEmpty()) continue;

            long matched = jdSkills.stream()
                    .map(this::normalize)
                    .filter(normalizedResumeSkills::contains)
                    .count();

            double score = (double) matched / jdSkills.size() * 100;

            if (score > bestScore) {
                bestScore = score;
                bestJobId = jd.getJobID();
                bestJobTitle = jd.getJobTitle();
            }
        }

        if (bestJobId == null) {
            return null; // no matches found
        }

        return MatchingResult.builder()
                .jobId(bestJobId)
                .jobTitle(bestJobTitle)
                .matchScore(bestScore)
                .build();
   }

    private String normalize(String input) {
        return input.toLowerCase().replaceAll("\\s+", "");
    }
}
