package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "\"Вид_результатов_базовых_учебных_д\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicActionResultType {
    @jakarta.persistence.Id
    private Long id;
    @Id
    @Column(name = "\"Код_обучамого_в_определенном_году\"")
    private Integer educationCode;

    @Column(name = "\"Описание_особенностей_результата_\"")
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "\"Код_обучамого_в_определенном_году\"")
    private PupilEducation education;
}