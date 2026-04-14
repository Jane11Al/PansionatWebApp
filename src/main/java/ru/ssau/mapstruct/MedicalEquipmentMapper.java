package ru.ssau.mapstruct;

import  ru.ssau.dto.MedicalEquipmentDto;
import  ru.ssau.entity.MedicalEquipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicalEquipmentMapper {
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    MedicalEquipmentDto toDto(MedicalEquipment entity);

    @Mapping(target = "pupil", ignore = true)
    MedicalEquipment toEntity(MedicalEquipmentDto dto);
}