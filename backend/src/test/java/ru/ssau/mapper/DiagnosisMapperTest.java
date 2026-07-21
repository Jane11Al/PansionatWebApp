package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.DiagnosisDto;
import ru.ssau.entity.Diagnosis;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiagnosisMapperTest {

    private static DiagnosisMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(DiagnosisMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Diagnosis entity = new Diagnosis();
        entity.setId(1L);
        entity.setCode("F70.0");
        entity.setName("Умственная отсталость легкой степени");

        DiagnosisDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getCode()).isEqualTo("F70.0");
        assertThat(dto.getName()).isEqualTo("Умственная отсталость легкой степени");
    }

    @Test
    void toEntity_ShouldMapAllFieldsExceptId() {
        DiagnosisDto dto = new DiagnosisDto();
        dto.setId(2L);
        dto.setCode("F71.0");
        dto.setName("Умственная отсталость умеренная");

        Diagnosis entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getCode()).isEqualTo("F71.0");
        assertThat(entity.getName()).isEqualTo("Умственная отсталость умеренная");
    }

    @Test
    void updateEntity_ShouldUpdateExistingEntity() {
        Diagnosis existing = new Diagnosis();
        existing.setId(3L);
        existing.setCode("F80.0");
        existing.setName("Старое название");

        DiagnosisDto dto = new DiagnosisDto();
        dto.setCode("F80.1");
        dto.setName("Новое название");

        mapper.updateEntity(dto, existing);

        assertThat(existing.getId()).isEqualTo(3L);
        assertThat(existing.getCode()).isEqualTo("F80.1");
        assertThat(existing.getName()).isEqualTo("Новое название");
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        Diagnosis d1 = new Diagnosis();
        d1.setId(1L);
        d1.setName("Диагноз 1");
        Diagnosis d2 = new Diagnosis();
        d2.setId(2L);
        d2.setName("Диагноз 2");

        List<DiagnosisDto> dtos = mapper.toDtoList(List.of(d1, d2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getName()).isEqualTo("Диагноз 1");
        assertThat(dtos.get(1).getName()).isEqualTo("Диагноз 2");
    }
}