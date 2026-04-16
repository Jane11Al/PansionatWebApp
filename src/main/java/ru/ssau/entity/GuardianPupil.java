package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"Опекун_воспитанника\"",
        uniqueConstraints = @UniqueConstraint(columnNames = {"\"ФИО_опекуна\"", "\"Номер_личного_дела_воспитанника\""}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuardianPupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "\"ФИО_опекуна\"", referencedColumnName = "\"ФИО_опекуна\"")
    private Guardian guardian;

    @ManyToOne
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"", referencedColumnName = "\"Номер_личного_дела_воспитанника\"")
    private Pupil pupil;
}