package com.example.filtersystem.context.criteria.infrastructure;

import com.example.filtersystem.context.criteria.domain.Criteria;
import com.example.filtersystem.context.criteria.domain.CriteriaRepository;
import com.example.filtersystem.context.filter.domain.Filter;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CriteriaJpaRepository extends CriteriaRepository, JpaRepository<CriteriaEntity, Long> {

    @Override
    default Criteria save(Criteria criteria, Filter filter) {
        CriteriaEntityMapper mapper = getMapper();
        return mapper.toDomain(save(mapper.toEntity(criteria, filter)));
    }

    @Override
    default Criteria update(Criteria criteria, Filter filter) {
        CriteriaEntityMapper mapper = getMapper();
        Optional<CriteriaEntity> optCriteria =  findById(criteria.getId());
        if (optCriteria.isEmpty()) {
            save(mapper.toEntity(criteria, filter));
        } else {
            CriteriaEntity criteriaEntity = optCriteria.get();
            mapper.updateWith(criteriaEntity, criteria);
            save(criteriaEntity);
        }
        return criteria;
    }

    @Override
    default List<Criteria> getByFilterId(Long id) {
        CriteriaEntityMapper mapper = getMapper();
        return findAllByFilterId(id).stream().map(mapper::toDomain).toList();
    }

    List<CriteriaEntity> findAllByFilterId(Long filterId);

    @Override
    default void deleteCriteria(Long id) {
        deleteById(id);
    }

    default CriteriaEntityMapper getMapper() {
        return Mappers.getMapper(CriteriaEntityMapper.class);
    }
}
