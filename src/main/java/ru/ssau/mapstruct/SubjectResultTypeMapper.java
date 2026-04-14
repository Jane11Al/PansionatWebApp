package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.SubjectResultTypeDto;
import ru.ssau.entity.SubjectResultType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectResultTypeMapper {
    SubjectResultTypeDto toDto(SubjectResultType entity);

    @Mapping(target = "education", ignore = true)
    @Mapping(target = "educationCode", ignore = true)
    SubjectResultType toEntity(SubjectResultTypeDto dto);
}