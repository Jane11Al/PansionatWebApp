package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "\"Программа\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Код_программы\"", unique = true, nullable = false)
    private Integer code;

    @Column(name = "\"Название\"")
    private String name;

    @OneToMany(mappedBy = "program")
    private Set<IndividualProgram> individualPrograms;
}