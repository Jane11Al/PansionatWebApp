package ru.ssau.dto;

import lombok.Data;

@Data
public class SubjectDto {
    private Long id;
    private String name;
    private Short hours;
    private Long subjectAreaId;
    private Integer subjectAreaCode;
    private String subjectAreaName;
    private Long individualProgramId;
    private Short year;
    private Integer programCode;
    private String programName;
}