// PupilEducationMapperTest.java
package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.PupilEducationDto;
import ru.ssau.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PupilEducationMapperTest {

    private static PupilEducationMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(PupilEducationMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        // Создаём связанные сущности
        Program program = new Program();
        program.setId(1L);
        program.setCode(101);
        program.setName("АООП вариант 1");

        IndividualProgram individualProgram = new IndividualProgram();
        individualProgram.setId(10L);
        individualProgram.setYear((short) 2024);
        individualProgram.setProgram(program);

        Teacher teacher = new Teacher();
        teacher.setId(2L);
        teacher.setFullName("Иванова Анна Сергеевна");

        Pupil pupil = new Pupil();
        pupil.setId(3L);
        pupil.setPersonalFileNumber(1001);
        pupil.setFullName("Петров Пётр");

        PupilEducation entity = new PupilEducation();
        entity.setId(5L);
        entity.setCode(3001);
        entity.setIndividualProgram(individualProgram);
        entity.setTeacher(teacher);
        entity.setPupil(pupil);
        entity.setSubjectIds(List.of(1L, 2L, 3L));

        PupilEducationDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getCode()).isEqualTo(3001);
        assertThat(dto.getIndividualProgramId()).isEqualTo(10L);
        assertThat(dto.getYear()).isEqualTo(2024);
        assertThat(dto.getProgramId()).isEqualTo(1L);
        assertThat(dto.getProgramCode()).isEqualTo(101);
        assertThat(dto.getProgramName()).isEqualTo("АООП вариант 1");
        assertThat(dto.getTeacherId()).isEqualTo(2L);
        assertThat(dto.getTeacherFullName()).isEqualTo("Иванова Анна Сергеевна");
        assertThat(dto.getPupilId()).isEqualTo(3L);
        assertThat(dto.getPupilPersonalFileNumber()).isEqualTo(1001);
        assertThat(dto.getPupilFullName()).isEqualTo("Петров Пётр");
        assertThat(dto.getSubjectIds()).containsExactly(1L, 2L, 3L);
    }

    @Test
    void toEntity_ShouldIgnoreNestedEntities() {
        PupilEducationDto dto = new PupilEducationDto();
        dto.setCode(4001);
        dto.setIndividualProgramId(20L);
        dto.setTeacherId(30L);
        dto.setPupilId(40L);
        dto.setSubjectIds(List.of(5L, 6L));

        PupilEducation entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getCode()).isEqualTo(4001);
        assertThat(entity.getIndividualProgram()).isNull();
        assertThat(entity.getTeacher()).isNull();
        assertThat(entity.getPupil()).isNull();
        // subjectIds маппится напрямую (если в маппере есть соответствующий маппинг)
        assertThat(entity.getSubjectIds()).containsExactly(5L, 6L);
    }

    @Test

    void updateEntity_ShouldUpdateExistingEntity() {
        PupilEducation existing = new PupilEducation();
        existing.setId(10L);
        existing.setCode(5001);
        existing.setSubjectIds(new ArrayList<>(List.of(1L)));

        PupilEducationDto dto = new PupilEducationDto();
        dto.setCode(5002);
        dto.setSubjectIds(List.of(2L, 3L));

        mapper.updateEntity(dto, existing);

        assertThat(existing.getId()).isEqualTo(10L);
        assertThat(existing.getCode()).isEqualTo(5002);
        assertThat(existing.getSubjectIds()).containsExactly(2L, 3L);
        assertThat(existing.getIndividualProgram()).isNull();
        assertThat(existing.getTeacher()).isNull();
        assertThat(existing.getPupil()).isNull();
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        PupilEducation pe1 = new PupilEducation();
        pe1.setId(1L);
        pe1.setCode(100);
        PupilEducation pe2 = new PupilEducation();
        pe2.setId(2L);
        pe2.setCode(200);

        List<PupilEducationDto> dtos = mapper.toDtoList(List.of(pe1, pe2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isEqualTo(1L);
        assertThat(dtos.get(0).getCode()).isEqualTo(100);
        assertThat(dtos.get(1).getId()).isEqualTo(2L);
        assertThat(dtos.get(1).getCode()).isEqualTo(200);
    }
}