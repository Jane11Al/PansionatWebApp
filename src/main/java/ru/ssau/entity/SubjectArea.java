package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "\"Предметная_область\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Код_предметной_области\"", unique = true, nullable = false)
    private Integer code;

    @Column(name = "\"Название_предметной_области\"")
    private String name;

    @OneToMany(mappedBy = "subjectArea")
    private Set<Subject> subjects;
}