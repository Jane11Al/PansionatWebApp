package ru.ssau.dto;

import lombok.Data;

@Data
public class GuardianPupilDto {
    private Long id;
    private Long guardianId;
    private String guardianFullName;
    private Long pupilId;
    private Integer pupilPersonalFileNumber;
    private String pupilFullName;
}