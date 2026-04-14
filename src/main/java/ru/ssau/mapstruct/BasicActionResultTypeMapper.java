package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.BasicActionResultTypeDto;
import ru.ssau.entity.BasicActionResultType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicActionResultTypeMapper {
    BasicActionResultTypeDto toDto(BasicActionResultType entity);

    @Mapping(target = "education", ignore = true)
    @Mapping(target = "educationCode", ignore = true) // если в DTO нет этого поля
    BasicActionResultType toEntity(BasicActionResultTypeDto dto);
}