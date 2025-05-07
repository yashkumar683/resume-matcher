package com.parser.parser_service.Services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.parser.parser_service.Repository.JobDescriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final JobDescriptionRepository jobDescriptionRepository;

    @Cacheable("knownSkills")
    public Set<String> getSkills() {
        return jobDescriptionRepository.findAll().stream()
                                    .flatMap(jd -> jd.getRequiredSkills().stream())
                                    .map(String::toLowerCase)
                                    .collect(Collectors.toSet());
    }
}
