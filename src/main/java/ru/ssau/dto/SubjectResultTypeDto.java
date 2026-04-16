package ru.ssau.dto;

import lombok.Data;

@Data
public class SubjectResultTypeDto {
    private Long id;
    private Long educationId;
    private Integer educationCode;
    private String description;
}