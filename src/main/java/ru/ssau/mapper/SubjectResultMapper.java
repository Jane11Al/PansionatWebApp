package ru.ssau.mapper;

import org.mapstruct.MappingTarget;
import ru.ssau.dto.SubjectResultDto;
import ru.ssau.entity.SubjectResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class, PupilEducationMapper.class})
public interface SubjectResultMapper {
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.name", target = "subjectName")
    @Mapping(source = "subject.subjectArea.id", target = "subjectAreaId")
    @Mapping(source = "subject.subjectArea.name", target = "subjectAreaName")
    @Mapping(source = "education.id", target = "educationId")
    @Mapping(source = "education.code", target = "educationCode")
    SubjectResultDto toDto(SubjectResult entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "education", ignore = true)
    SubjectResult toEntity(SubjectResultDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "education", ignore = true)
    void updateEntity(SubjectResultDto dto, @MappingTarget SubjectResult entity);

    List<SubjectResultDto> toDtoList(List<SubjectResult> entities);
}