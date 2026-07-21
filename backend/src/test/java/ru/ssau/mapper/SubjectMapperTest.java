package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.SubjectDto;
import ru.ssau.entity.IndividualProgram;
import ru.ssau.entity.Program;
import ru.ssau.entity.Subject;
import ru.ssau.entity.SubjectArea;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectMapperTest {

    private static SubjectMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(SubjectMapper.class);
    }

    @Test
    void toDto_ShouldMapNestedEntities() {
        SubjectArea area = new SubjectArea();
        area.setId(1L);
        area.setCode(101);
        area.setName("Язык и речевая практика");

        Program program = new Program();
        program.setId(10L);
        program.setCode(1001);
        program.setName("АООП вариант 1");

        IndividualProgram ip = new IndividualProgram();
        ip.setId(5L);
        ip.setYear((short) 2024);
        ip.setProgram(program);

        Subject entity = new Subject();
        entity.setId(3L);
        entity.setName("Русский язык");
        entity.setHours((short) 68);
        entity.setSubjectArea(area);
        entity.setIndividualProgram(ip);

        SubjectDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(3L);
        assertThat(dto.getName()).isEqualTo("Русский язык");
        assertThat(dto.getHours()).isEqualTo((short) 68);
        assertThat(dto.getSubjectAreaId()).isEqualTo(1L);
        assertThat(dto.getSubjectAreaCode()).isEqualTo(101);
        assertThat(dto.getSubjectAreaName()).isEqualTo("Язык и речевая практика");
        assertThat(dto.getIndividualProgramId()).isEqualTo(5L);
        assertThat(dto.getYear()).isEqualTo((short) 2024);
                assertThat(dto.getProgramCode()).isEqualTo(1001);
        assertThat(dto.getProgramName()).isEqualTo("АООП вариант 1");
    }

    @Test
    void toEntity_ShouldIgnoreNestedEntities() {
        SubjectDto dto = new SubjectDto();
        dto.setName("Математика");
        dto.setHours((short) 72);
        dto.setSubjectAreaId(2L);
        dto.setIndividualProgramId(10L);

        Subject entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getName()).isEqualTo("Математика");
        assertThat(entity.getHours()).isEqualTo((short) 72);
        assertThat(entity.getSubjectArea()).isNull();
        assertThat(entity.getIndividualProgram()).isNull();
    }
}