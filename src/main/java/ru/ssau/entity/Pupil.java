package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"Воспитанник\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Номер_личного_дела_воспитанника\"", unique = true, nullable = false)
    private Integer personalFileNumber;

    @Column(name = "\"ФИО\"")
    private String fullName;

    @Column(name = "\"Дата_рождения\"")
    private LocalDate birthDate;

    @Column(name = "\"Пол\"")
    private Character gender;

    @Column(name = "\"Основной_диагноз\"")
    private String mainDiagnosis;

    @OneToMany(mappedBy = "pupil")
    private Set<DiagnosisPupil> diagnoses;

    @OneToMany(mappedBy = "pupil")
    private Set<PupilEducation> educations;

    @OneToMany(mappedBy = "pupil")
    private Set<MedicalEquipment> equipments;

    @OneToMany(mappedBy = "pupil")
    private Set<GuardianPupil> guardians;
}