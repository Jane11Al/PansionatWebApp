package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.DiagnosisPupilDto;
import ru.ssau.entity.Diagnosis;
import ru.ssau.entity.DiagnosisPupil;
import ru.ssau.entity.Pupil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiagnosisPupilMapperTest {

    private static DiagnosisPupilMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(DiagnosisPupilMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1L);
        diagnosis.setCode("F70.0");
        diagnosis.setName("Умственная отсталость");

        Pupil pupil = new Pupil();
        pupil.setId(10L);
        pupil.setPersonalFileNumber(1001);
        pupil.setFullName("Иванов Иван");

        DiagnosisPupil entity = new DiagnosisPupil();
        entity.setId(5L);
        entity.setDiagnosis(diagnosis);
        entity.setPupil(pupil);

        DiagnosisPupilDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getDiagnosisId()).isEqualTo(1L);
        assertThat(dto.getDiagnosisCode()).isEqualTo("F70.0");
        assertThat(dto.getDiagnosisName()).isEqualTo("Умственная отсталость");
        assertThat(dto.getPupilId()).isEqualTo(10L);
        assertThat(dto.getPupilPersonalFileNumber()).isEqualTo(1001);
        assertThat(dto.getPupilFullName()).isEqualTo("Иванов Иван");
    }

    @Test
    void toEntity_ShouldIgnoreNestedEntities() {
        DiagnosisPupilDto dto = new DiagnosisPupilDto();
        dto.setDiagnosisId(2L);
        dto.setPupilId(20L);

        DiagnosisPupil entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getDiagnosis()).isNull();
        assertThat(entity.getPupil()).isNull();
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        DiagnosisPupil dp1 = new DiagnosisPupil();
        dp1.setId(1L);
        DiagnosisPupil dp2 = new DiagnosisPupil();
        dp2.setId(2L);

        List<DiagnosisPupilDto> dtos = mapper.toDtoList(List.of(dp1, dp2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isEqualTo(1L);
        assertThat(dtos.get(1).getId()).isEqualTo(2L);
    }
}