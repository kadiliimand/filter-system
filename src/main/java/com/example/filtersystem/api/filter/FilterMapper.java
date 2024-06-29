package com.example.filtersystem.api.filter;

import com.example.filtersystem.api.criteria.CriteriaMapper;
import com.example.filtersystem.context.filter.domain.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CriteriaMapper.class)
public interface FilterMapper {

    @Mapping(source = "criteriaList", target = "criteriaList")
    FilterDto toDto(Filter filter);

    @Mapping(source = "criteriaList", target = "criteriaList")
    Filter toDomain(FilterDto filterDto);
}
