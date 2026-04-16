package ru.ssau.dto;

import lombok.Data;

@Data
public class DiagnosisPupilDto {
    private Long id;
    private Long diagnosisId;
    private String diagnosisCode;
    private String diagnosisName;
    private Long pupilId;
    private Integer pupilPersonalFileNumber;
    private String pupilFullName;
}
