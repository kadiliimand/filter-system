package com.example.filtersystem.context.criteria.domain;

import com.example.filtersystem.context.filter.domain.Filter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CriteriaServiceTest {

    @Mock
    private CriteriaRepository criteriaRepository;

    @InjectMocks
    private CriteriaService criteriaService;

    @Test
    void shouldSaveCriteria() {
        // given
        Criteria criteria = new Criteria();
        criteria.setValue("criteria value");
        Filter filter = new Filter();
        filter.setTitle("filter title");
        when(criteriaRepository.save(criteria, filter)).thenReturn(criteria);

        // when
        Criteria saved = criteriaService.saveCriteria(criteria, filter);

        // then
        assertNotNull(saved);
        verify(criteriaRepository).save(saved, filter);
        assertEquals("criteria value", saved.getValue());
    }

    @Test
    void shouldUpdateCriteria() {
        // given
        Criteria criteria = new Criteria();
        criteria.setValue("criteria value");
        Filter filter = new Filter();
        filter.setTitle("filter title");
        when(criteriaRepository.update(criteria, filter)).thenReturn(criteria);

        // when
        Criteria updated = criteriaService.updateCriteria(criteria, filter);

        // then
        assertNotNull(updated);
        verify(criteriaRepository).update(updated, filter);
        assertEquals("criteria value", updated.getValue());
    }

    @Test
    void shouldUpdateCriteriaList() {
        // given
        Criteria criteria = new Criteria();
        criteria.setValue("criteria value");
        Filter filter = new Filter();
        filter.setTitle("filter title");
        filter.setCriteriaList(List.of(criteria));
        when(criteriaRepository.getByFilterId(filter.getId())).thenReturn(List.of(criteria));
        when(criteriaRepository.update(criteria, filter)).thenReturn(criteria);

        // when
        var updated = criteriaService.updateCriteriaList(filter, filter);

        // then
        assertNotNull(updated);
        verify(criteriaRepository).getByFilterId(filter.getId());
        verify(criteriaRepository).update(criteria, filter);
        assertEquals("criteria value", updated.get(0).getValue());
    }
}
