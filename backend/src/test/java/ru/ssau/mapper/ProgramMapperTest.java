package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.ProgramDto;
import ru.ssau.entity.Program;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramMapperTest {

    private static ProgramMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(ProgramMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Program entity = new Program();
        entity.setId(1L);
        entity.setCode(101);
        entity.setName("АООП вариант 1");

        ProgramDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getCode()).isEqualTo(101);
        assertThat(dto.getName()).isEqualTo("АООП вариант 1");
    }

    @Test
    void toEntity_ShouldMapAllFieldsExceptId() {
        ProgramDto dto = new ProgramDto();
        dto.setId(2L);
        dto.setCode(102);
        dto.setName("АООП вариант 2");

        Program entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getCode()).isEqualTo(102);
        assertThat(entity.getName()).isEqualTo("АООП вариант 2");
    }

    @Test
    void updateEntity_ShouldUpdateExistingEntity() {
        Program existing = new Program();
        existing.setId(3L);
        existing.setCode(103);
        existing.setName("Старое название");

        ProgramDto dto = new ProgramDto();
        dto.setCode(104);
        dto.setName("Новое название");

        mapper.updateEntity(dto, existing);

        assertThat(existing.getId()).isEqualTo(3L);
        assertThat(existing.getCode()).isEqualTo(104);
        assertThat(existing.getName()).isEqualTo("Новое название");
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        Program p1 = new Program();
        p1.setId(1L);
        p1.setName("Программа 1");
        Program p2 = new Program();
        p2.setId(2L);
        p2.setName("Программа 2");

        List<ProgramDto> dtos = mapper.toDtoList(List.of(p1, p2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getName()).isEqualTo("Программа 1");
        assertThat(dtos.get(1).getName()).isEqualTo("Программа 2");
    }
}