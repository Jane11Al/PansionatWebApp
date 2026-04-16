package ru.ssau.dto;

import lombok.Data;

@Data
public class IndividualProgramDto {
    private Long id;
    private Short year;
    private Long programId;
    private Integer programCode;
    private String programName;
}