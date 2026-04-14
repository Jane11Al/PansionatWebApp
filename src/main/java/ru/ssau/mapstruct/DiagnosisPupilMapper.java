package ru.ssau.mapstruct;

import ru.ssau.dto.DiagnosisPupilDto;
import ru.ssau.entity.DiagnosisPupil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiagnosisPupilMapper {
    @Mapping(source = "diagnosis.name", target = "diagnosisName")
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    @Mapping(source = "id.diagnosisCode", target = "diagnosisCode")
    @Mapping(source = "id.personalFileNumber", target = "personalFileNumber")
    DiagnosisPupilDto toDto(DiagnosisPupil entity);

    @Mapping(target = "id.diagnosisCode", source = "diagnosisCode")
    @Mapping(target = "id.personalFileNumber", source = "personalFileNumber")
    @Mapping(target = "diagnosis", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    DiagnosisPupil toEntity(DiagnosisPupilDto dto);
}