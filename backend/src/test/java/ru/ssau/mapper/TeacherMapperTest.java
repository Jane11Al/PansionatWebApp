package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.TeacherDto;
import ru.ssau.entity.Teacher;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TeacherMapperTest {

    private static TeacherMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(TeacherMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Teacher entity = new Teacher();
        entity.setId(1L);
        entity.setFullName("Сидорова Анна");
        entity.setBirthDate(LocalDate.of(1985, 3, 20));
        entity.setContactInfo("+79161234567");
        entity.setPosition("Логопед");
        entity.setRole("ROLE_TEACHER");

        TeacherDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getFullName()).isEqualTo("Сидорова Анна");
        assertThat(dto.getBirthDate()).isEqualTo(LocalDate.of(1985, 3, 20));
        assertThat(dto.getContactInfo()).isEqualTo("+79161234567");
        assertThat(dto.getPosition()).isEqualTo("Логопед");
        assertThat(dto.getRole()).isEqualTo("ROLE_TEACHER");
    }

    @Test
    void toEntity_ShouldIgnorePasswordAndId() {
        TeacherDto dto = new TeacherDto();
        dto.setFullName("Новый Учитель");
        dto.setRole("ROLE_ADMIN");

        Teacher entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getPassword()).isNull();
        assertThat(entity.getFullName()).isEqualTo("Новый Учитель");
        assertThat(entity.getRole()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    void updateEntity_ShouldUpdateFieldsButNotPassword() {
        Teacher existing = new Teacher();
        existing.setId(2L);
        existing.setFullName("Старое Имя");
        existing.setPassword("hashed_password");
        existing.setRole("ROLE_TEACHER");

        TeacherDto dto = new TeacherDto();
        dto.setFullName("Новое Имя");
        dto.setRole("ROLE_ADMIN");

        mapper.updateEntity(dto, existing);

        assertThat(existing.getId()).isEqualTo(2L);
        assertThat(existing.getPassword()).isEqualTo("hashed_password");
        assertThat(existing.getFullName()).isEqualTo("Новое Имя");
        assertThat(existing.getRole()).isEqualTo("ROLE_ADMIN");
    }
}