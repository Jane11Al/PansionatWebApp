package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"Вид_личностых_результатов\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalResultType {
    @Id
    @Column(name = "\"Код_обучамого_в_определенном_году\"")
    private Integer educationCode;

    @Column(name = "\"Описание_особенностей_личностног\"")
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "\"Код_обучамого_в_определенном_году\"")
    private PupilEducation education;
}
