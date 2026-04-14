package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.SubjectAreaDto;
import ru.ssau.entity.SubjectArea;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectAreaMapper {
    SubjectAreaDto toDto(SubjectArea entity);

    @Mapping(target = "subjects", ignore = true)
    SubjectArea toEntity(SubjectAreaDto dto);
}