package ru.ssau.mapstruct;

import ru.ssau.dto.SubjectResultDto;
import ru.ssau.entity.SubjectResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectResultMapper {
    @Mapping(source = "id.subjectName", target = "subjectName")
    @Mapping(source = "id.subjectAreaCode", target = "subjectAreaCode")
    @Mapping(source = "id.educationCode", target = "educationCode")
    @Mapping(source = "subject.subjectArea.name", target = "subjectAreaName")
    SubjectResultDto toDto(SubjectResult entity);

    @Mapping(target = "id.subjectName", source = "subjectName")
    @Mapping(target = "id.subjectAreaCode", source = "subjectAreaCode")
    @Mapping(target = "id.educationCode", source = "educationCode")
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "education", ignore = true)
    SubjectResult toEntity(SubjectResultDto dto);
}
