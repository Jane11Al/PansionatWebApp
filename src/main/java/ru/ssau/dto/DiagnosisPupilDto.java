package ru.ssau.dto;

import lombok.Data;

@Data
public class DiagnosisPupilDto {
    private String diagnosisCode;
    private Integer personalFileNumber;
    private String diagnosisName;   // из Diagnosis
    private String pupilFullName;   // из Pupil
}