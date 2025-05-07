package com.parser.parser_service.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParsedResume {
   private String parsedText;
   private String email;
   private String mobile;
   private List<String> skills; 
}
