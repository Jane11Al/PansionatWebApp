package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"Опекун_воспитанника\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardianPupil {
    @Id
    @Column(name = "\"Номер_личного_дела_воспитанника\"")
    private Integer personalFileNumber;  // В исходных данных PK только по воспитаннику!

    @ManyToOne
    @JoinColumn(name = "\"ФИО_опекуна\"")
    private Guardian guardian;

    @ManyToOne
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"", insertable = false, updatable = false)
    private Pupil pupil;
}