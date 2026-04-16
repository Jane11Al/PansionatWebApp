package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.BasicActionResultTypeDto;
import ru.ssau.entity.BasicActionResultType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PupilEducationMapper.class})
public interface BasicActionResultTypeMapper {
    @Mapping(source = "education.id", target = "educationId")
    @Mapping(source = "education.code", target = "educationCode")
    BasicActionResultTypeDto toDto(BasicActionResultType entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "education", ignore = true)
    BasicActionResultType toEntity(BasicActionResultTypeDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "education", ignore = true)
    void updateEntity(BasicActionResultTypeDto dto, @MappingTarget BasicActionResultType entity);

    List<BasicActionResultTypeDto> toDtoList(List<BasicActionResultType> entities);
}