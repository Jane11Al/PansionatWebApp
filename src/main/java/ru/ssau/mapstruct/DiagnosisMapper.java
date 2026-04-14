package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.DiagnosisDto;
import ru.ssau.entity.Diagnosis;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {
    DiagnosisDto toDto(Diagnosis entity);

    @Mapping(target = "pupils", ignore = true)
    Diagnosis toEntity(DiagnosisDto dto);
}