package ru.ssau.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TeacherDto {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String contactInfo;
    private String position;
    private String role;
}