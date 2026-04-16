package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"Результат_изучения_учебных_предме\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "\"Код_обучамого_в_определенном_году\"", referencedColumnName = "\"Код_обучамого_в_определенном_году\"")
    private PupilEducation education;

    @Column(name = "\"Описание_цели_обучения\"")
    private String goalDescription;

    @Column(name = "\"Описание_результатов_обучения\"")
    private String resultDescription;
}