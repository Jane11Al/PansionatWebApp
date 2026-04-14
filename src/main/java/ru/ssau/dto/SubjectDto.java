package ru.ssau.dto;

import lombok.Data;

@Data
public class SubjectDto {
    private String name;
    private Integer subjectAreaCode;
    private String subjectAreaName;
    private Short hours;
    private Integer programCode;
    private Short year;
    private String programName;
}