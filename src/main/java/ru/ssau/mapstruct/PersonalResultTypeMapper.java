package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.PersonalResultTypeDto;
import ru.ssau.entity.PersonalResultType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonalResultTypeMapper {
    PersonalResultTypeDto toDto(PersonalResultType entity);

    @Mapping(target = "education", ignore = true)
    PersonalResultType toEntity(PersonalResultTypeDto dto);
}