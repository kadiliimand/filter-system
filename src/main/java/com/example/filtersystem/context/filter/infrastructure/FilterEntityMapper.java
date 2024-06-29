package com.example.filtersystem.context.filter.infrastructure;

import com.example.filtersystem.context.filter.domain.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FilterEntityMapper {

    FilterEntityMapper INSTANCE = Mappers.getMapper(FilterEntityMapper.class);

    @Mapping(target = "filterSequence", source = "sequence")
    @Mapping(target = "criteriaList", ignore = true)
    FilterEntity toEntity(Filter filter);

    @Mapping(target = "sequence", source = "filterSequence")
    @Mapping(target = "criteriaList", ignore = true)
    Filter toDomain(FilterEntity filterEntity);

    @Mapping(target = "title", source = "filter.title")
    @Mapping(target = "filterSequence", source = "filter.sequence")
    @Mapping(target = "criteriaList", ignore = true)
    void updateWith(@MappingTarget FilterEntity filterEntity, Filter filter);
}
