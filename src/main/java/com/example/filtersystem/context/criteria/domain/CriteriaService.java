package com.example.filtersystem.context.criteria.domain;

import com.example.filtersystem.context.filter.domain.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CriteriaService {

    private final CriteriaRepository criteriaRepository;

    public Criteria saveCriteria(Criteria criteria, Filter filter) {
        return criteriaRepository.save(criteria, filter);
    }

    public Criteria updateCriteria(Criteria criteria, Filter filter) {
        return criteriaRepository.update(criteria, filter);
    }

    public List<Criteria> updateCriteriaList(Filter item, Filter filter) {
        List<Criteria> existingCriteria = criteriaRepository.getByFilterId(filter.getId());
        List<Criteria> criteriaToDelete = existingCriteria.stream()
                .filter(existing -> item.getCriteriaList().stream().noneMatch(newCriteria ->
                        Objects.equals(newCriteria.getId(), existing.getId()))).toList();
        criteriaToDelete.forEach(criteria -> criteriaRepository.deleteCriteria(criteria.getId()));
        return item.getCriteriaList().stream()
                .map(criteria -> updateCriteria(criteria, filter))
                .toList();
    }
}
