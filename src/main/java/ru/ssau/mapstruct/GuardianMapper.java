package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.GuardianDto;
import ru.ssau.entity.Guardian;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuardianMapper {
    GuardianDto toDto(Guardian entity);

    @Mapping(target = "pupils", ignore = true)
    Guardian toEntity(GuardianDto dto);
}