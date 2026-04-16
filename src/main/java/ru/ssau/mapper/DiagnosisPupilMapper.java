package ru.ssau.mapper;

import org.mapstruct.MappingTarget;
import ru.ssau.dto.DiagnosisPupilDto;
import ru.ssau.entity.DiagnosisPupil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DiagnosisMapper.class, PupilMapper.class})
public interface DiagnosisPupilMapper {
    @Mapping(source = "diagnosis.id", target = "diagnosisId")
    @Mapping(source = "diagnosis.code", target = "diagnosisCode")
    @Mapping(source = "diagnosis.name", target = "diagnosisName")
    @Mapping(source = "pupil.id", target = "pupilId")
    @Mapping(source = "pupil.personalFileNumber", target = "pupilPersonalFileNumber")
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    DiagnosisPupilDto toDto(DiagnosisPupil entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diagnosis", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    DiagnosisPupil toEntity(DiagnosisPupilDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diagnosis", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    void updateEntity(DiagnosisPupilDto dto, @MappingTarget DiagnosisPupil entity);

    List<DiagnosisPupilDto> toDtoList(List<DiagnosisPupil> entities);
}