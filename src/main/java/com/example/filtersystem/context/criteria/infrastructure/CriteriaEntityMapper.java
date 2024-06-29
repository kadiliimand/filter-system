package com.example.filtersystem.context.criteria.infrastructure;

import com.example.filtersystem.context.criteria.domain.Criteria;
import com.example.filtersystem.context.filter.domain.Filter;
import com.example.filtersystem.context.filter.infrastructure.FilterEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {FilterEntityMapper.class})
public interface CriteriaEntityMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "criterionValue", source = "criteria.value")
        @Mapping(target = "filter", expression = "java(com.example.filtersystem.context.filter.infrastructure.FilterEntityMapper.INSTANCE.toEntity(filter))")
        CriteriaEntity toEntity(Criteria criteria, Filter filter);

        @Mapping(target = "filterId", source = "filter.id")
        @Mapping(target = "value", source = "criterionValue")
        @Mapping(target = "type", source = "type")
        Criteria toDomain(CriteriaEntity criteriaEntity);

        @Mapping(target = "id", source = "criteria.id")
        @Mapping(target = "criterionValue", source = "criteria.value")
        @Mapping(target = "type", source = "criteria.type")
        @Mapping(target = "condition", source = "criteria.condition")
        @Mapping(target = "filter", ignore = true)
        void updateWith(@MappingTarget CriteriaEntity criteriaEntity, Criteria criteria);
}
