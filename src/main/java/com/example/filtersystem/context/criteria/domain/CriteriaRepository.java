package com.example.filtersystem.context.criteria.domain;

import com.example.filtersystem.context.filter.domain.Filter;

import java.util.List;

public interface CriteriaRepository {

    Criteria save(Criteria criteria, Filter filter);

    Criteria update(Criteria criteria, Filter filter);

    List<Criteria> getByFilterId(Long id);

    void deleteCriteria(Long id);
}
