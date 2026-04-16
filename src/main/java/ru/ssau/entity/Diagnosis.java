package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "\"Диагноз\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Код_заболевания\"", unique = true, nullable = false)
    private String code;

    @Column(name = "\"Название\"")
    private String name;

    @OneToMany(mappedBy = "diagnosis")
    private Set<DiagnosisPupil> pupils;
}