package ru.ssau.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PupilDto {
    private Integer personalFileNumber;
    private String fullName;
    private LocalDate birthDate;
    private Character gender;
    private String mainDiagnosis;
}