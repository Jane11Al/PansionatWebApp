package ru.ssau.dto;

import lombok.Data;

@Data
public class PupilEducationDto {
    private Integer code;
    private Short year;
    private Integer programCode;
    private String programName;
    private String teacherFullName;
    private Integer personalFileNumber;
    private String pupilFullName;
}