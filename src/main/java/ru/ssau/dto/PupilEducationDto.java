package ru.ssau.dto;

import lombok.Data;

@Data
public class PupilEducationDto {
    private Long id;
    private Integer code;
    private Long individualProgramId;
    private Short year;
    private Integer programCode;
    private String programName;
    private Long teacherId;
    private String teacherFullName;
    private Long pupilId;
    private Integer pupilPersonalFileNumber;
    private String pupilFullName;
}