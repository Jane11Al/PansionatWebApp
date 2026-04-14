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
public class SubjectResultId implements Serializable {
    @Column(name = "\"Название_учебного_предмета\"")
    private String subjectName;

    @Column(name = "\"Код_предметной_области\"")
    private Integer subjectAreaCode;

    @Column(name = "\"Код_обучамого_в_определенном_году\"")
    private Integer educationCode;
}