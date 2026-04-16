package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "\"Мед_оборудование\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Инвентарный_номер_оборудования\"", unique = true, nullable = false)
    private Integer inventoryNumber;

    @Column(name = "\"Название\"")
    private String name;

    @Column(name = "\"Дата_выдачи\"")
    private LocalDate issueDate;

    @ManyToOne
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"", referencedColumnName = "\"Номер_личного_дела_воспитанника\"")
    private Pupil pupil;
}