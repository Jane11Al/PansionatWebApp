package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "\"Учебный_предмет\"",
        uniqueConstraints = @UniqueConstraint(columnNames = {"\"Название_учебного_предмета\"", "\"Код_предметной_области\""}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Название_учебного_предмета\"", nullable = false)
    private String name;

    @Column(name = "\"Объем_в_часах\"")
    private Short hours;

    @ManyToOne
    @JoinColumn(name = "\"Код_предметной_области\"", referencedColumnName = "\"Код_предметной_области\"")
    private SubjectArea subjectArea;

    @ManyToOne
    @JoinColumn(name = "individual_program_id")
    private IndividualProgram individualProgram;

    @OneToMany(mappedBy = "subject")
    private Set<SubjectResult> results;
}