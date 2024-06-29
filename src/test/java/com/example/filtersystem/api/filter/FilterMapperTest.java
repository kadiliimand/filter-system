package com.example.filtersystem.api.filter;

import com.example.filtersystem.api.criteria.CriteriaDto;
import com.example.filtersystem.context.criteria.domain.Criteria;
import com.example.filtersystem.context.filter.domain.Filter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FilterMapperTest {

    private final FilterMapper filterMapper = Mappers.getMapper(FilterMapper.class);

    @Test
    void shouldMapFilterToDto() {
        // given
        Filter filter = new Filter();
        filter.setTitle("title");

        // when
        FilterDto filterDto = filterMapper.toDto(filter);

        // then
        assertNotNull(filterDto);
        assertEquals("title", filterDto.getTitle());
    }

    @Test
    void shouldMapDtoToFilter() {
        // given
        FilterDto filterDto = new FilterDto();
        filterDto.setSequence(1);

        // when
        Filter filter = filterMapper.toDomain(filterDto);

        // then
        assertNotNull(filter);
        assertEquals(filterDto.getSequence(), filter.getSequence());
    }
}
