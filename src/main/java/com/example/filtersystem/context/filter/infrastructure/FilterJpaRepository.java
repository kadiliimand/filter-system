package com.example.filtersystem.context.filter.infrastructure;

import com.example.filtersystem.context.criteria.domain.Criteria;
import com.example.filtersystem.context.criteria.infrastructure.CriteriaEntityMapper;
import com.example.filtersystem.context.filter.domain.Filter;
import com.example.filtersystem.context.filter.domain.FilterRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FilterJpaRepository extends FilterRepository, JpaRepository<FilterEntity, Long> {

    @Override
    default Filter update(Filter filter) {
        FilterEntityMapper mapper = getMapper();
        FilterEntity filterEntity = findById(filter.getId())
                .orElseThrow(() -> new RuntimeException("Filter not found"));
        mapper.updateWith(filterEntity, filter);
        return mapper.toDomain(save(filterEntity));
    }

    @Override
    default Filter save(Filter filter) {
        FilterEntityMapper mapper = getMapper();
        return mapper.toDomain(save(mapper.toEntity(filter)));
    }

    @Override
    default List<Filter> getAll() {
        FilterEntityMapper mapper = getMapper();
        CriteriaEntityMapper criteriaMapper = getCriteriaMapper();
        List<FilterEntity> filterEntities = findAll();
        List<Filter> filters = new ArrayList<>();
        for (FilterEntity filterEntity : filterEntities) {
            List<Criteria> criteria = filterEntity.getCriteriaList().stream()
                    .map(criteriaMapper::toDomain)
                    .toList();
            Filter filter = mapper.toDomain(filterEntity);
            filter.setCriteriaList(criteria);
            filters.add(filter);
        }
        return filters;
    }

    @Override
    default Optional<Filter> getByFilterSequence(Integer filterSequence) {
        FilterEntityMapper mapper = getMapper();
        return findByFilterSequence(filterSequence).map(mapper::toDomain);
    }

    Optional<FilterEntity> findByFilterSequence(Integer filterSequence);

    Optional<FilterEntity> findById(Long id);

    @Override
    default void deleteFilterById(Long id) {
        deleteById(id);
    }

    @Override
    default Optional<Filter> getFilterById(Long id) {
        FilterEntityMapper mapper = getMapper();
        return findById(id).map(mapper::toDomain);
    }

    @Override
    default List<Filter> findBySequenceGreaterThan(Integer deletedSequence) {
        FilterEntityMapper mapper = getMapper();
        return findAllByFilterSequenceGreaterThan(deletedSequence).stream()
                .map(mapper::toDomain)
                .toList();
    }

    List<FilterEntity> findAllByFilterSequenceGreaterThan(Integer filterSequence);

    default FilterEntityMapper getMapper() {
        return Mappers.getMapper(FilterEntityMapper.class);
    }

    default CriteriaEntityMapper getCriteriaMapper() {
        return Mappers.getMapper(CriteriaEntityMapper.class);
    }
}
