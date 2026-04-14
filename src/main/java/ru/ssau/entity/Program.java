package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "\"Программа\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @Id
    @Column(name = "\"Код_программы\"")
    private Integer code;

    @Column(name = "\"Название\"")
    private String name;

    @OneToMany(mappedBy = "program")
    private Set<IndividualProgram> individualPrograms;
}