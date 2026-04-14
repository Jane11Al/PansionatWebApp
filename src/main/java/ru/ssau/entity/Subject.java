package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "\"Учебный_предмет\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @EmbeddedId
    private SubjectId id;

    @Column(name = "\"Объем_в_часах\"")
    private Short hours;

    @ManyToOne
    @MapsId("subjectAreaCode")
    @JoinColumn(name = "\"Код_предметной_области\"")
    private SubjectArea subjectArea;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "\"Год_обучения\"", referencedColumnName = "\"Год_обучения\""),
            @JoinColumn(name = "\"Код_программы\"", referencedColumnName = "\"Код_программы\""),
    })
    private IndividualProgram individualProgram;

    @OneToMany(mappedBy = "subject")
    private Set<SubjectResult> results;
}

