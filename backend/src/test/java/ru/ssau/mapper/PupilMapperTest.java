package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.PupilDto;
import ru.ssau.entity.Pupil;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PupilMapperTest {

    private static PupilMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(PupilMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Pupil entity = new Pupil();
        entity.setId(1L);
        entity.setPersonalFileNumber(1001);
        entity.setFullName("Иванов Иван");
        entity.setBirthDate(LocalDate.of(2010, 5, 15));
        entity.setGender('М');
        entity.setMainDiagnosis("F70.0");

        PupilDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getPersonalFileNumber()).isEqualTo(1001);
        assertThat(dto.getFullName()).isEqualTo("Иванов Иван");
        assertThat(dto.getBirthDate()).isEqualTo(LocalDate.of(2010, 5, 15));
        assertThat(dto.getGender()).isEqualTo('М');
        assertThat(dto.getMainDiagnosis()).isEqualTo("F70.0");
    }

    @Test
    void toEntity_ShouldMapAllFieldsExceptId() {
        PupilDto dto = new PupilDto();
        dto.setId(2L);
        dto.setPersonalFileNumber(1002);
        dto.setFullName("Петров Пётр");
        dto.setBirthDate(LocalDate.of(2012, 3, 20));
        dto.setGender('М');
        dto.setMainDiagnosis("F71.0");

        Pupil entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getPersonalFileNumber()).isEqualTo(1002);
        assertThat(entity.getFullName()).isEqualTo("Петров Пётр");
        assertThat(entity.getBirthDate()).isEqualTo(LocalDate.of(2012, 3, 20));
        assertThat(entity.getGender()).isEqualTo('М');
        assertThat(entity.getMainDiagnosis()).isEqualTo("F71.0");
    }

    @Test
    void updateEntity_ShouldUpdateExistingEntity() {
        Pupil existing = new Pupil();
        existing.setId(3L);
        existing.setPersonalFileNumber(1003);
        existing.setFullName("Старое Имя");
        existing.setBirthDate(LocalDate.of(2011, 1, 1));
        existing.setGender('Ж');
        existing.setMainDiagnosis("F80.0");

        PupilDto dto = new PupilDto();
        dto.setPersonalFileNumber(1004);
        dto.setFullName("Новое Имя");
        dto.setBirthDate(LocalDate.of(2015, 8, 8));
        dto.setGender('М');
        dto.setMainDiagnosis("F90.0");

        mapper.updateEntity(dto, existing);

        assertThat(existing.getId()).isEqualTo(3L);
        assertThat(existing.getPersonalFileNumber()).isEqualTo(1004);
        assertThat(existing.getFullName()).isEqualTo("Новое Имя");
        assertThat(existing.getBirthDate()).isEqualTo(LocalDate.of(2015, 8, 8));
        assertThat(existing.getGender()).isEqualTo('М');
        assertThat(existing.getMainDiagnosis()).isEqualTo("F90.0");
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        Pupil pupil1 = new Pupil();
        pupil1.setId(1L);
        pupil1.setFullName("Иванов");
        Pupil pupil2 = new Pupil();
        pupil2.setId(2L);
        pupil2.setFullName("Петров");

        var dtos = mapper.toDtoList(List.of(pupil1, pupil2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getFullName()).isEqualTo("Иванов");
        assertThat(dtos.get(1).getFullName()).isEqualTo("Петров");
    }
}