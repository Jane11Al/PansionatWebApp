package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"Индивидуальная_программа\"",
        uniqueConstraints = @UniqueConstraint(columnNames = {"\"Год_обучения\"", "\"Код_программы\""}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Год_обучения\"", nullable = false)
    private Short year;

    @ManyToOne
    @JoinColumn(name = "\"Код_программы\"", referencedColumnName = "\"Код_программы\"")
    private Program program;
}