package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.GuardianDto;
import ru.ssau.entity.Guardian;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuardianMapper {
    GuardianDto toDto(Guardian entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pupils", ignore = true)
    Guardian toEntity(GuardianDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pupils", ignore = true)
    void updateEntity(GuardianDto dto, @MappingTarget Guardian entity);

    List<GuardianDto> toDtoList(List<Guardian> entities);
}