package com.parser.parser_service.Interfaces;

import java.util.List;

import com.parser.parser_service.DTO.MatchingResult;

public interface MatchInterface {
    public MatchingResult findBestMatch(List<String> resumeSkills);
}
