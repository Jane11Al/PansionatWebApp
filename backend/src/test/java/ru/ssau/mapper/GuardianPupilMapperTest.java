package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.GuardianPupilDto;
import ru.ssau.entity.Guardian;
import ru.ssau.entity.GuardianPupil;
import ru.ssau.entity.Pupil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GuardianPupilMapperTest {

    private static GuardianPupilMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(GuardianPupilMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Guardian guardian = new Guardian();
        guardian.setId(1L);
        guardian.setFullName("Иванова Мария");

        Pupil pupil = new Pupil();
        pupil.setId(10L);
        pupil.setPersonalFileNumber(1001);
        pupil.setFullName("Иванов Иван");

        GuardianPupil entity = new GuardianPupil();
        entity.setId(5L);
        entity.setGuardian(guardian);
        entity.setPupil(pupil);

        GuardianPupilDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getGuardianId()).isEqualTo(1L);
        assertThat(dto.getGuardianFullName()).isEqualTo("Иванова Мария");
        assertThat(dto.getPupilId()).isEqualTo(10L);
        assertThat(dto.getPupilPersonalFileNumber()).isEqualTo(1001);
        assertThat(dto.getPupilFullName()).isEqualTo("Иванов Иван");
    }

    @Test
    void toEntity_ShouldIgnoreNestedEntities() {
        GuardianPupilDto dto = new GuardianPupilDto();
        dto.setGuardianId(2L);
        dto.setPupilId(20L);

        GuardianPupil entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getGuardian()).isNull();
        assertThat(entity.getPupil()).isNull();
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        GuardianPupil gp1 = new GuardianPupil();
        gp1.setId(1L);
        GuardianPupil gp2 = new GuardianPupil();
        gp2.setId(2L);

        List<GuardianPupilDto> dtos = mapper.toDtoList(List.of(gp1, gp2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isEqualTo(1L);
        assertThat(dtos.get(1).getId()).isEqualTo(2L);
    }
}