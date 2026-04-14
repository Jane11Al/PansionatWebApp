package ru.ssau.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalEquipmentDto {
    private Integer inventoryNumber;
    private String name;
    private LocalDate issueDate;
    private Integer personalFileNumber;
    private String pupilFullName;
}