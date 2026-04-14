package ru.ssau.mapstruct;

import ru.ssau.dto.PupilEducationDto;
import ru.ssau.entity.PupilEducation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PupilEducationMapper {
    @Mapping(source = "individualProgram.id.year", target = "year")
    @Mapping(source = "individualProgram.id.programCode", target = "programCode")
    @Mapping(source = "individualProgram.program.name", target = "programName")
    @Mapping(source = "teacher.fullName", target = "teacherFullName")
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    PupilEducationDto toDto(PupilEducation entity);

    @Mapping(target = "individualProgram", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    @Mapping(target = "subjectResults", ignore = true)
    @Mapping(target = "personalResult", ignore = true)
    @Mapping(target = "subjectResultType", ignore = true)
    @Mapping(target = "basicActionResult", ignore = true)
    PupilEducation toEntity(PupilEducationDto dto);
}