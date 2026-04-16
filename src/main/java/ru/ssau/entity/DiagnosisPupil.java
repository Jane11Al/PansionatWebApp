package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"Диагноз_воспитанника\"",
        uniqueConstraints = @UniqueConstraint(columnNames = {"\"Код_заболевания\"", "\"Номер_личного_дела_воспитанника\""}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisPupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "\"Код_заболевания\"", referencedColumnName = "\"Код_заболевания\"")
    private Diagnosis diagnosis;

    @ManyToOne
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"", referencedColumnName = "\"Номер_личного_дела_воспитанника\"")
    private Pupil pupil;
}