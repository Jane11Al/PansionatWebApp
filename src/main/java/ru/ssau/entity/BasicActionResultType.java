package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"Вид_результатов_базовых_учебных_действий\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicActionResultType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "\"Код_обучамого_в_определенном_году\"", referencedColumnName = "\"Код_обучамого_в_определенном_году\"", unique = true)
    private PupilEducation education;

    @Column(name = "\"Описание_особенностей_результата_базовых_учебных_действий\"")
    private String description;
}