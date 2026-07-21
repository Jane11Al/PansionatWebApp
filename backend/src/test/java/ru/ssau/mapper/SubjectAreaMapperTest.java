// SubjectAreaMapperTest.java
package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.SubjectAreaDto;
import ru.ssau.entity.SubjectArea;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectAreaMapperTest {

    private static SubjectAreaMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(SubjectAreaMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        SubjectArea entity = new SubjectArea();
        entity.setId(1L);
        entity.setCode(1);
        entity.setName("Язык и речевая практика");

        SubjectAreaDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getCode()).isEqualTo(1);
        assertThat(dto.getName()).isEqualTo("Язык и речевая практика");
    }

    @Test
    void toEntity_ShouldMapAllFieldsExceptId() {
        SubjectAreaDto dto = new SubjectAreaDto();
        dto.setId(2L);
        dto.setCode(2);
        dto.setName("Математика");

        SubjectArea entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getCode()).isEqualTo(2);
        assertThat(entity.getName()).isEqualTo("Математика");
    }

    @Test
    void updateEntity_ShouldUpdateExistingEntity() {
        SubjectArea existing = new SubjectArea();
        existing.setId(3L);
        existing.setCode(3);
        existing.setName("Старое название");

        SubjectAreaDto dto = new SubjectAreaDto();
        dto.setCode(4);
        dto.setName("Новое название");

        mapper.updateEntity(dto, existing);

        assertThat(existing.getId()).isEqualTo(3L);
        assertThat(existing.getCode()).isEqualTo(4);
        assertThat(existing.getName()).isEqualTo("Новое название");
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        SubjectArea sa1 = new SubjectArea();
        sa1.setId(1L);
        sa1.setName("Область 1");
        SubjectArea sa2 = new SubjectArea();
        sa2.setId(2L);
        sa2.setName("Область 2");

        List<SubjectAreaDto> dtos = mapper.toDtoList(List.of(sa1, sa2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getName()).isEqualTo("Область 1");
        assertThat(dtos.get(1).getName()).isEqualTo("Область 2");
    }
}