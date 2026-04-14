package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.PupilDto;
import ru.ssau.entity.Pupil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PupilMapper {
    PupilDto toDto(Pupil entity);

    @Mapping(target = "diagnoses", ignore = true)
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    @Mapping(target = "guardians", ignore = true)
    Pupil toEntity(PupilDto dto);
}