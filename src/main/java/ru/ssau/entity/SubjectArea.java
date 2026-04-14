package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "\"Предметная_область\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectArea {
    @Id
    @Column(name = "\"Код_предметной_области\"")
    private Integer code;

    @Column(name = "\"Название_предметной_области\"")
    private String name;

    @OneToMany(mappedBy = "subjectArea")
    private Set<Subject> subjects;
}