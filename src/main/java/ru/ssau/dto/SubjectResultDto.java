package ru.ssau.dto;

import lombok.Data;

@Data
public class SubjectResultDto {
    private String subjectName;
    private Integer subjectAreaCode;
    private String subjectAreaName;
    private Integer educationCode;
    private String goalDescription;
    private String resultDescription;
}