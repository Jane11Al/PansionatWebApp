package ru.ssau.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalEquipmentDto {
    private Long id;
    private Integer inventoryNumber;
    private String name;
    private LocalDate issueDate;
    private Long pupilId;
    private Integer pupilPersonalFileNumber;
    private String pupilFullName;
}