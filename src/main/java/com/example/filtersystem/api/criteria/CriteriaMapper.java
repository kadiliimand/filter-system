package com.example.filtersystem.api.criteria;

import com.example.filtersystem.context.criteria.domain.Criteria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CriteriaMapper {

        CriteriaDto toDto(Criteria criteria);

        Criteria toDomain(CriteriaDto criteriaDto);
}
