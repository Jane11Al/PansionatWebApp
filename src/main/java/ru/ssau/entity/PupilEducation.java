package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "\"Обучение_воспитанника\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PupilEducation {
    @Id
    @Column(name = "\"Код_обучамого_в_определенном_году\"")
    private Integer code;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "\"Год_обучения\"", referencedColumnName = "\"Год_обучения\""),
            @JoinColumn(name = "\"Код_программы\"", referencedColumnName = "\"Код_программы\"")

    })
    private IndividualProgram individualProgram;

    @ManyToOne
    @JoinColumn(name = "\"ФИО_педагога\"")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"")
    private Pupil pupil;

    @OneToMany(mappedBy = "education")
    private Set<SubjectResult> subjectResults;

    @OneToOne(mappedBy = "education")
    private PersonalResultType personalResult;

    @OneToOne(mappedBy = "education")
    private SubjectResultType subjectResultType;

    @OneToOne(mappedBy = "education")
    private BasicActionResultType basicActionResult;
}