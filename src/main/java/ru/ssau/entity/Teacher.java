package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"Педагог\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @Column(name = "\"ФИО_педагога\"")
    private String fullName;

    @Column(name = "\"Дата_рождения\"")
    private LocalDate birthDate;

    @Column(name = "\"Контактная_информация\"")
    private String contactInfo;

    @Column(name = "\"Должность\"")
    private String position;

    @OneToMany(mappedBy = "teacher")
    private Set<PupilEducation> educations;
}