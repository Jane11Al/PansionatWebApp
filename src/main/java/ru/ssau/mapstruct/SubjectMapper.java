package ru.ssau.mapstruct;

import ru.ssau.dto.SubjectDto;
import ru.ssau.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(source = "id.name", target = "name")
    @Mapping(source = "id.subjectAreaCode", target = "subjectAreaCode")
    @Mapping(source = "subjectArea.name", target = "subjectAreaName")
    @Mapping(source = "individualProgram.id.year", target = "year")
    @Mapping(source = "individualProgram.id.programCode", target = "programCode")
    @Mapping(source = "individualProgram.program.name", target = "programName")
    SubjectDto toDto(Subject entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjectArea", ignore = true)
    @Mapping(target = "individualProgram", ignore = true)
    @Mapping(target = "results", ignore = true)
    Subject toEntity(SubjectDto dto);
}