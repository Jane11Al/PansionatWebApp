package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.IndividualProgramDto;
import ru.ssau.entity.IndividualProgram;
import ru.ssau.entity.Program;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IndividualProgramMapperTest {

    private static IndividualProgramMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(IndividualProgramMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Program program = new Program();
        program.setId(10L);
        program.setCode(101);
        program.setName("АООП вариант 1");

        IndividualProgram entity = new IndividualProgram();
        entity.setId(1L);
        entity.setYear((short) 2024);
        entity.setProgram(program);

        IndividualProgramDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getYear()).isEqualTo((short) 2024);
        assertThat(dto.getProgramId()).isEqualTo(10L);
        assertThat(dto.getProgramCode()).isEqualTo(101);
        assertThat(dto.getProgramName()).isEqualTo("АООП вариант 1");
    }

    @Test
    void toEntity_ShouldIgnoreNestedEntities() {
        IndividualProgramDto dto = new IndividualProgramDto();
        dto.setYear((short) 2025);
        dto.setProgramId(5L);

        IndividualProgram entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getYear()).isEqualTo((short) 2025);
        assertThat(entity.getProgram()).isNull();
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        IndividualProgram ip1 = new IndividualProgram();
        ip1.setId(1L);
        IndividualProgram ip2 = new IndividualProgram();
        ip2.setId(2L);

        List<IndividualProgramDto> dtos = mapper.toDtoList(List.of(ip1, ip2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isEqualTo(1L);
        assertThat(dtos.get(1).getId()).isEqualTo(2L);
    }
}