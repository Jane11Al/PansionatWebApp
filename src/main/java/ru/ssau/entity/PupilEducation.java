package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"Обучение_воспитанника\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Код_обучамого_в_определенном_году\"", unique = true, nullable = false)
    private Integer code;

    @ManyToOne
    @JoinColumn(name = "individual_program_id")
    private IndividualProgram individualProgram;

    @ManyToOne
    @JoinColumn(name = "\"ФИО_педагога\"", referencedColumnName = "\"ФИО_педагога\"")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"", referencedColumnName = "\"Номер_личного_дела_воспитанника\"")
    private Pupil pupil;

    @OneToOne(mappedBy = "education")
    private PersonalResultType personalResult;

    @OneToOne(mappedBy = "education")
    private SubjectResultType subjectResultType;

    @OneToOne(mappedBy = "education")
    private BasicActionResultType basicActionResult;
}