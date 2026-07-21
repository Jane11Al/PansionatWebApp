package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.GuardianDto;
import ru.ssau.entity.Guardian;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GuardianMapperTest {

    private static GuardianMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(GuardianMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Guardian entity = new Guardian();
        entity.setId(1L);
        entity.setFullName("Иванова Мария");
        entity.setContactInfo("+79164567890");
        entity.setAddress("г. Москва, ул. Ленина, д.5");
        entity.setGender('Ж');
        entity.setBirthDate(LocalDate.of(1985, 5, 10));

        GuardianDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getFullName()).isEqualTo("Иванова Мария");
        assertThat(dto.getContactInfo()).isEqualTo("+79164567890");
        assertThat(dto.getAddress()).isEqualTo("г. Москва, ул. Ленина, д.5");
        assertThat(dto.getGender()).isEqualTo('Ж');
        assertThat(dto.getBirthDate()).isEqualTo(LocalDate.of(1985, 5, 10));
    }

    @Test
    void toEntity_ShouldMapAllFieldsExceptId() {
        GuardianDto dto = new GuardianDto();
        dto.setId(2L);
        dto.setFullName("Петров Пётр");
        dto.setContactInfo("+79161234567");

        Guardian entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getFullName()).isEqualTo("Петров Пётр");
        assertThat(entity.getContactInfo()).isEqualTo("+79161234567");
    }

    @Test
    void updateEntity_ShouldUpdateExistingEntity() {
        Guardian existing = new Guardian();
        existing.setId(3L);
        existing.setFullName("Старое имя");

        GuardianDto dto = new GuardianDto();
        dto.setFullName("Новое имя");

        mapper.updateEntity(dto, existing);

        assertThat(existing.getId()).isEqualTo(3L);
        assertThat(existing.getFullName()).isEqualTo("Новое имя");
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        Guardian g1 = new Guardian();
        g1.setId(1L);
        g1.setFullName("Опекун 1");
        Guardian g2 = new Guardian();
        g2.setId(2L);
        g2.setFullName("Опекун 2");

        List<GuardianDto> dtos = mapper.toDtoList(List.of(g1, g2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getFullName()).isEqualTo("Опекун 1");
        assertThat(dtos.get(1).getFullName()).isEqualTo("Опекун 2");
    }
}