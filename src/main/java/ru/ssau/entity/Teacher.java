package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"Педагог\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"ФИО_педагога\"", unique = true, nullable = false)
    private String fullName;   // используется как логин

    @Column(name = "\"Дата_рождения\"")
    private LocalDate birthDate;

    @Column(name = "\"Контактная_информация\"")
    private String contactInfo;

    @Column(name = "\"Должность\"")
    private String position;

    @Column(nullable = false)
    private String password;   // хешированный пароль

    @Column(nullable = false)
    private String role;       // например "ROLE_TEACHER" или "ROLE_ADMIN"

    @OneToMany(mappedBy = "teacher")
    private Set<PupilEducation> educations;
}