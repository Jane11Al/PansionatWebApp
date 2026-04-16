package ru.ssau.mapper;

import org.mapstruct.MappingTarget;
import  ru.ssau.dto.MedicalEquipmentDto;
import  ru.ssau.entity.MedicalEquipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PupilMapper.class})
public interface MedicalEquipmentMapper {
    @Mapping(source = "pupil.id", target = "pupilId")
    @Mapping(source = "pupil.personalFileNumber", target = "pupilPersonalFileNumber")
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    MedicalEquipmentDto toDto(MedicalEquipment entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    MedicalEquipment toEntity(MedicalEquipmentDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    void updateEntity(MedicalEquipmentDto dto, @MappingTarget MedicalEquipment entity);

    List<MedicalEquipmentDto> toDtoList(List<MedicalEquipment> entities);
}