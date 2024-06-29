package com.example.filtersystem.context.filter.domain;

import com.example.filtersystem.context.criteria.domain.Criteria;
import com.example.filtersystem.context.criteria.domain.CriteriaService;
import com.example.filtersystem.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;
    private final CriteriaService criteriaService;

    public List<Filter> getAllFilters() {
        return filterRepository.getAll();
    }

    public Filter getFilterByFilterSequence(Integer filterSequence) {
        return filterRepository.getByFilterSequence(filterSequence)
                .orElseThrow(()-> new NotFoundException("Entity not found"));
    }

    public Filter saveFilter(Filter item) {
        Filter filter;
        if (item.getId() != 0) {
            filter = filterRepository.update(item);
            filter.setCriteriaList(criteriaService.updateCriteriaList(item, filter));
        } else {
            filter = filterRepository.save(item);
            List<Criteria> savedCriteria = item.getCriteriaList().stream()
                    .map(criteria -> criteriaService.saveCriteria(criteria, filter))
                    .toList();
            filter.setCriteriaList(savedCriteria);
        }
        return filter;
    }

    public void deleteFilter(Long id) {
        Filter filterToDelete = filterRepository.getFilterById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filter not found"));

        Integer deletedSequence = filterToDelete.getSequence();
        filterRepository.deleteFilterById(id);
        adjustSequenceNumbers(deletedSequence);
    }

    private void adjustSequenceNumbers(Integer deletedSequence) {
        List<Filter> filtersToUpdate = filterRepository.findBySequenceGreaterThan(deletedSequence);
        for (Filter filter : filtersToUpdate) {
            filter.setSequence(filter.getSequence() - 1);
            filterRepository.update(filter);
        }
    }
}
