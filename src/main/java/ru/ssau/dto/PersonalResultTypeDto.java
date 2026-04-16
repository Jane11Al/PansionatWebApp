package ru.ssau.dto;

import lombok.Data;

@Data
public class PersonalResultTypeDto {
    private Long id;
    private Long educationId;
    private Integer educationCode;
    private String description;
}