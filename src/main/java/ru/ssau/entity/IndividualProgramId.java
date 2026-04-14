package ru.ssau.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualProgramId implements Serializable {

    @Column(name = "\"Год_обучения\"")
    private Short year;

    @Column(name = "\"Код_программы\"")
    private Integer programCode;
}