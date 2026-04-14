package ru.ssau.dto;

import lombok.Data;

@Data
public class GuardianPupilDto {
    private String guardianFullName;
    private Integer personalFileNumber;
    private String pupilFullName;
}