package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "\"Воспитанник\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pupil {
    @Id
    @Column(name = "\"Номер_личного_дела_воспитанника\"")
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