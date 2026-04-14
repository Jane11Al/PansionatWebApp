package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "\"Мед_оборудование\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalEquipment {
    @Id
    @Column(name = "\"Инвентарный_номер_оборудования\"")
    private Integer inventoryNumber;

    @Column(name = "\"Название\"")
    private String name;

    @Column(name = "\"Дата_выдачи\"")
    private LocalDate issueDate;

    @ManyToOne
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"")
    private Pupil pupil;
}