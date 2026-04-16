package ru.ssau.mapper;

import org.mapstruct.*;
import ru.ssau.dto.PupilDto;
import ru.ssau.entity.Pupil;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PupilMapper {
    PupilDto toDto(Pupil entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diagnoses", ignore = true)
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    @Mapping(target = "guardians", ignore = true)
    Pupil toEntity(PupilDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diagnoses", ignore = true)
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    @Mapping(target = "guardians", ignore = true)
    void updateEntity(PupilDto dto, @MappingTarget Pupil entity);

    List<PupilDto> toDtoList(List<Pupil> entities);
}