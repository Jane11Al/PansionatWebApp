package ru.ssau.dto;

import lombok.Data;

@Data
public class SubjectResultDto {
    private Long id;
    private Long subjectId;
    private String subjectName;
    private Long subjectAreaId;
    private String subjectAreaName;
    private Long educationId;
    private Integer educationCode;
    private String goalDescription;
    private String resultDescription;
}