package com.example.filtersystem.context.filter.domain;

import com.example.filtersystem.context.criteria.domain.Criteria;
import com.example.filtersystem.context.criteria.domain.CriteriaService;
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
public class FilterServiceTest {

    @Mock
    private FilterRepository filterRepository;
    @Mock
    private CriteriaService criteriaService;

    @InjectMocks
    private FilterService filterService;

    @Test
    void shouldGetAllFilters() {
        // given
        Filter filter = new Filter();
        filter.setTitle("title");
        when(filterRepository.getAll()).thenReturn(List.of(filter));

        // when
        List<Filter> filters = filterService.getAllFilters();

        // then
        assertNotNull(filters);
        assertEquals(1, filters.size());
    }

    @Test
    void shouldGetFilterByFilterSequence() {
        // given
        Filter filter = new Filter();
        filter.setTitle("title");
        when(filterRepository.getByFilterSequence(1)).thenReturn(java.util.Optional.of(filter));

        // when
        Filter result = filterService.getFilterByFilterSequence(1);

        // then
        assertNotNull(result);
        assertEquals("title", result.getTitle());
    }

    @Test
    void shouldSaveNewFilter() {
        // given
        Filter filter = new Filter();
        filter.setTitle("title");
        filter.setId(0L);
        Criteria criteria = new Criteria();
        criteria.setValue("value");
        filter.setCriteriaList(List.of(criteria));
        when(filterRepository.save(filter)).thenReturn(filter);

        // when
        Filter result = filterService.saveFilter(filter);

        // then
        assertNotNull(result);
        assertEquals("title", result.getTitle());
        assertEquals(filter.getCriteriaList().size(), result.getCriteriaList().size());
    }

    @Test
    void shouldUpdateFilter() {
        // given
        Filter filter = new Filter();
        filter.setTitle("title");
        filter.setId(1L);
        Criteria criteria = new Criteria();
        criteria.setValue("value");
        filter.setCriteriaList(List.of(criteria));
        when(filterRepository.update(filter)).thenReturn(filter);

        // when
        Filter result = filterService.saveFilter(filter);

        // then
        assertNotNull(result);
        assertEquals("title", result.getTitle());
        assertEquals(filter.getCriteriaList().size(), result.getCriteriaList().size());
    }

    @Test
    void shouldDeleteFilter() {
        // given
        Filter filter = new Filter();
        filter.setSequence(1);
        when(filterRepository.getFilterById(1L)).thenReturn(java.util.Optional.of(filter));

        // when
        filterService.deleteFilter(1L);

        // then
        verify(filterRepository).deleteFilterById(1L);
    }
}
