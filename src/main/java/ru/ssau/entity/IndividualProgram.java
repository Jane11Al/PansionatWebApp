package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "\"Индивидуальная_программа\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualProgram {
    @EmbeddedId
    private IndividualProgramId id;

    @ManyToOne
    @MapsId("programCode")
    @JoinColumn(name = "\"Код_программы\"")
    private Program program;

    @OneToMany(mappedBy = "individualProgram")
    private Set<PupilEducation> educations;

    @OneToMany(mappedBy = "individualProgram")
    private Set<Subject> subjects;
}