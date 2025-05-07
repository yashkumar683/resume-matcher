package com.parser.parser_service.Interfaces;

import com.parser.parser_service.DTO.ParsedResume;

public interface ResumeParser {
    public ParsedResume parse(byte[] resume, String fileName);
}
