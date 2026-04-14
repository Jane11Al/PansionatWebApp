package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"Опекун\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guardian {
    @Id
    @Column(name = "\"ФИО_опекуна\"")
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