package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.DiagnosisDto;
import ru.ssau.entity.Diagnosis;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {
    DiagnosisDto toDto(Diagnosis entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pupils", ignore = true)
    Diagnosis toEntity(DiagnosisDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pupils", ignore = true)
    void updateEntity(DiagnosisDto dto, @MappingTarget Diagnosis entity);

    List<DiagnosisDto> toDtoList(List<Diagnosis> entities);
}