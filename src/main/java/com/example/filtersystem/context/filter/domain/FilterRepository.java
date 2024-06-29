package com.example.filtersystem.context.filter.domain;

import java.util.List;
import java.util.Optional;

public interface FilterRepository {

    Filter update(Filter filter);

    Filter save(Filter filter);

    List<Filter> getAll();

    Optional<Filter> getByFilterSequence(Integer sequence);

    Optional<Filter> getFilterById(Long id);

    void deleteFilterById(Long id);

    List<Filter> findBySequenceGreaterThan(Integer deletedSequence);
}
