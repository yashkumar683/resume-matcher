package com.parser.parser_service.Services;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Service;

import com.parser.parser_service.DTO.ParsedResume;
import com.parser.parser_service.Interfaces.ResumeParser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasicResumeParser implements ResumeParser {

    private final Tika tika = new Tika();
    private final SkillService skillService;

    @Override
    public ParsedResume parse(byte[] resume, String fileName) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(resume);

            Metadata metadata = new Metadata();
            metadata.set("resourceName", fileName);

            String parsedText = tika.parseToString(byteArrayInputStream, metadata);
            return ParsedResume.builder()
                            .parsedText(parsedText)
                            .email(extractEmail(parsedText))
                            .mobile(extractPhone(parsedText))
                            .skills(extractSkills(parsedText))
                            .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Something went wrong while parsing!");
        }
    }

    private String extractEmail(String text) {
        Matcher m = Pattern.compile("\\b[\\w.-]+@[\\w.-]+\\.\\w{2,4}\\b").matcher(text);
        return m.find() ? m.group() : null;
    }

    private String extractPhone(String text) {
        Matcher m = Pattern.compile("\\b\\d{10}\\b").matcher(text);
        return m.find() ? m.group() : null;
    }

    private List<String> extractSkills(String resumeText) {
        Set<String> knownSkills = skillService.getSkills();
        log.info("Known skills--" + knownSkills);

        // Normalize resume text for matching
        String lowerText = resumeText.toLowerCase().replaceAll("\\s+", "");

        return knownSkills.stream()
                .map(skill -> skill.toLowerCase().replaceAll("\\s+", ""))
                .filter(lowerText::contains)
                .collect(Collectors.toList());
    }
}
