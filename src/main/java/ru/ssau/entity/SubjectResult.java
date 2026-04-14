package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "\"Результат_изучения_учебных_предметов_воспитанника\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResult {
    @EmbeddedId
    private SubjectResultId id;

    @Column(name = "\"Описание_цели_обучения\"")
    private String goalDescription;

    @Column(name = "\"Описание_результатов_обучения\"")
    private String resultDescription;

    @ManyToOne
    @MapsId("subject")
    @JoinColumns({
            @JoinColumn(name = "\"Название_учебного_предмета\"", referencedColumnName = "\"Название_учебного_предмета\""),
            @JoinColumn(name = "\"Код_предметной_области\"", referencedColumnName = "\"Код_предметной_области\"")
    })
    private Subject subject;

    @ManyToOne
    @MapsId("educationCode")
    @JoinColumn(name = "\"Код_обучамого_в_определенном_году\"")
    private PupilEducation education;
}

