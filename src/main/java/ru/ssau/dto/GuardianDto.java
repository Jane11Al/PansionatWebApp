package ru.ssau.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GuardianDto {
    private Long id;
    private String fullName;
    private String contactInfo;
    private String address;
    private Character gender;
    private LocalDate birthDate;
}