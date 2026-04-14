package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "\"Диагноз\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {
    @Id
    @Column(name = "\"Код_заболевания\"")
    private String code;

    @Column(name = "\"Название\"")
    private String name;

    @OneToMany(mappedBy = "diagnosis")
    private Set<DiagnosisPupil> pupils;
}