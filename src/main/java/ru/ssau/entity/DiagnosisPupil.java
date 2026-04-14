package ru.ssau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"Диагноз_воспитанника\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisPupil {
    @EmbeddedId
    private DiagnosisPupilId id;

    @ManyToOne
    @MapsId("diagnosisCode")
    @JoinColumn(name = "\"Код_заболевания\"")
    private Diagnosis diagnosis;

    @ManyToOne
    @MapsId("personalFileNumber")
    @JoinColumn(name = "\"Номер_личного_дела_воспитанника\"")
    private Pupil pupil;
}