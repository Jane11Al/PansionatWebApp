// MedicalEquipmentMapperTest.java
package ru.ssau.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.ssau.dto.MedicalEquipmentDto;
import ru.ssau.entity.MedicalEquipment;
import ru.ssau.entity.Pupil;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MedicalEquipmentMapperTest {

    private static MedicalEquipmentMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = Mappers.getMapper(MedicalEquipmentMapper.class);
    }

    @Test
    void toDto_ShouldMapAllFields() {
        Pupil pupil = new Pupil();
        pupil.setId(1L);
        pupil.setPersonalFileNumber(1001);
        pupil.setFullName("Иванов Иван");

        MedicalEquipment entity = new MedicalEquipment();
        entity.setId(5L);
        entity.setInventoryNumber(5001);
        entity.setName("Ингалятор");
        entity.setIssueDate(LocalDate.of(2024, 1, 15));
        entity.setPupil(pupil);

        MedicalEquipmentDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getInventoryNumber()).isEqualTo(5001);
        assertThat(dto.getName()).isEqualTo("Ингалятор");
        assertThat(dto.getIssueDate()).isEqualTo(LocalDate.of(2024, 1, 15));
        assertThat(dto.getPupilId()).isEqualTo(1L);
        assertThat(dto.getPupilPersonalFileNumber()).isEqualTo(1001);
        assertThat(dto.getPupilFullName()).isEqualTo("Иванов Иван");
    }

    @Test
    void toEntity_ShouldIgnoreNestedEntities() {
        MedicalEquipmentDto dto = new MedicalEquipmentDto();
        dto.setInventoryNumber(6001);
        dto.setName("Небулайзер");
        dto.setPupilId(10L);

        MedicalEquipment entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getInventoryNumber()).isEqualTo(6001);
        assertThat(entity.getName()).isEqualTo("Небулайзер");
        assertThat(entity.getPupil()).isNull();
    }

    @Test
    void toDtoList_ShouldMapListOfEntities() {
        MedicalEquipment me1 = new MedicalEquipment();
        me1.setId(1L);
        MedicalEquipment me2 = new MedicalEquipment();
        me2.setId(2L);

        List<MedicalEquipmentDto> dtos = mapper.toDtoList(List.of(me1, me2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isEqualTo(1L);
        assertThat(dtos.get(1).getId()).isEqualTo(2L);
    }
}