package ru.ssau.mapper;

import org.mapstruct.MappingTarget;
import ru.ssau.dto.PupilEducationDto;
import ru.ssau.entity.PupilEducation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class, PupilMapper.class, IndividualProgramMapper.class})
public interface PupilEducationMapper {
    @Mapping(source = "individualProgram.id", target = "individualProgramId")
    @Mapping(source = "individualProgram.year", target = "year")
    @Mapping(source = "individualProgram.program.code", target = "programCode")
    @Mapping(source = "individualProgram.program.name", target = "programName")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.fullName", target = "teacherFullName")
    @Mapping(source = "pupil.id", target = "pupilId")
    @Mapping(source = "pupil.personalFileNumber", target = "pupilPersonalFileNumber")
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    PupilEducationDto toDto(PupilEducation entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "individualProgram", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    @Mapping(target = "personalResult", ignore = true)
    @Mapping(target = "subjectResultType", ignore = true)
    @Mapping(target = "basicActionResult", ignore = true)
    PupilEducation toEntity(PupilEducationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "individualProgram", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    @Mapping(target = "personalResult", ignore = true)
    @Mapping(target = "subjectResultType", ignore = true)
    @Mapping(target = "basicActionResult", ignore = true)
    void updateEntity(PupilEducationDto dto, @MappingTarget PupilEducation entity);

    List<PupilEducationDto> toDtoList(List<PupilEducation> entities);
}
