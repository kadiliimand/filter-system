package com.example.filtersystem.context.filter.infrastructure;

import com.example.filtersystem.context.filter.domain.Filter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FilterEntityMapperTest {

    private final FilterEntityMapper mapper = Mappers.getMapper(FilterEntityMapper.class);

    @Test
    void shouldMapFilterToEntity() {
        // given
        Filter filter = new Filter();
        filter.setTitle("title");

        // when
        FilterEntity filterEntity = mapper.toEntity(filter);

        // then
        assertNotNull(filterEntity);
        assertEquals(filter.getTitle(), filterEntity.getTitle());
    }

    @Test
    void shouldMapEntityToFilter() {
        // given
        FilterEntity filterEntity = new FilterEntity();
        filterEntity.setTitle("title");

        // when
        Filter filter = mapper.toDomain(filterEntity);

        // then
        assertNotNull(filter);
        assertEquals(filterEntity.getTitle(), filter.getTitle());
    }

    @Test
    void shouldUpdateEntityWithFilter() {
        // given
        FilterEntity filterEntity = new FilterEntity();
        Filter filter = new Filter();
        filter.setTitle("title");
        filter.setSequence(1);

        // when
        mapper.updateWith(filterEntity, filter);

        // then
        assertEquals(filter.getTitle(), filterEntity.getTitle());
        assertEquals(filter.getSequence(), filterEntity.getFilterSequence());
    }
}
