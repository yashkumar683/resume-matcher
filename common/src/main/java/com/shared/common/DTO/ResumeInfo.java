package com.shared.common.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResumeInfo {
    private String fileName;
    private String resumeKey;
    private String status;

    public ResumeInfo(String fileName, String resumeKey, String status) {
        this.fileName = fileName;
        this.resumeKey = resumeKey;
        this.status = status;
    }

}

