package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"Опекун\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"ФИО_опекуна\"", unique = true, nullable = false)
    private String fullName;

    @Column(name = "\"Контактная_информация\"")
    private String contactInfo;

    @Column(name = "\"Адрес\"")
    private String address;

    @Column(name = "\"Пол\"")
    private Character gender;

    @Column(name = "\"Дата_рождения\"")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "guardian")
    private Set<GuardianPupil> pupils;
}