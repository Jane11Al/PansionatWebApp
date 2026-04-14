package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisPupilId implements Serializable {
    @Column(name = "\"Код_заболевания\"")
    private String diagnosisCode;

    @Column(name = "\"Номер_личного_дела_воспитанника\"")
    private Integer personalFileNumber;
}