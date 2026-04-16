package ru.ssau.mapper;

import org.mapstruct.MappingTarget;
import ru.ssau.dto.SubjectDto;
import ru.ssau.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubjectAreaMapper.class, IndividualProgramMapper.class})
public interface SubjectMapper {
    @Mapping(source = "subjectArea.id", target = "subjectAreaId")
    @Mapping(source = "subjectArea.code", target = "subjectAreaCode")
    @Mapping(source = "subjectArea.name", target = "subjectAreaName")
    @Mapping(source = "individualProgram.id", target = "individualProgramId")
    @Mapping(source = "individualProgram.year", target = "year")
    @Mapping(source = "individualProgram.program.code", target = "programCode")
    @Mapping(source = "individualProgram.program.name", target = "programName")
    SubjectDto toDto(Subject entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjectArea", ignore = true)
    @Mapping(target = "individualProgram", ignore = true)
    @Mapping(target = "results", ignore = true)
    Subject toEntity(SubjectDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjectArea", ignore = true)
    @Mapping(target = "individualProgram", ignore = true)
    @Mapping(target = "results", ignore = true)
    void updateEntity(SubjectDto dto, @MappingTarget Subject entity);

    List<SubjectDto> toDtoList(List<Subject> entities);
}
