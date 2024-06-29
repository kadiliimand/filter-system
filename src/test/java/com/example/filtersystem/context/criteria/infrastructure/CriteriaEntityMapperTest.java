package com.example.filtersystem.context.criteria.infrastructure;

import com.example.filtersystem.context.criteria.domain.Criteria;
import com.example.filtersystem.context.filter.domain.Filter;
import com.example.filtersystem.context.filter.infrastructure.FilterEntity;
import com.example.filtersystem.context.filter.infrastructure.FilterEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CriteriaEntityMapperTest {

    @Mock
    private FilterEntityMapper filterEntityMapper;

    @InjectMocks
    private final CriteriaEntityMapper mapper = Mappers.getMapper(CriteriaEntityMapper.class);

    @Test
    void shouldMapCriteriaToEntity() {
        // given
        Criteria criteria = new Criteria();
        criteria.setValue("value");
        Filter filter = new Filter();
        filter.setSequence(1);
        FilterEntity filterEntity = new FilterEntity();
        filterEntity.setFilterSequence(1);

        // when
        CriteriaEntity criteriaEntity = mapper.toEntity(criteria, filter);

        // then
        assertNotNull(criteriaEntity);
        assertEquals(criteria.getValue(), criteriaEntity.getCriterionValue());
    }

    @Test
    void shouldMapEntityToCriteria() {
        // given
        CriteriaEntity criteriaEntity = new CriteriaEntity();
        criteriaEntity.setCriterionValue("value");

        // when
        Criteria criteria = mapper.toDomain(criteriaEntity);

        // then
        assertNotNull(criteria);
        assertEquals(criteriaEntity.getCriterionValue(), criteria.getValue());
    }

    @Test
    void shouldUpdateEntityWithCriteria() {
        // given
        CriteriaEntity criteriaEntity = new CriteriaEntity();
        Criteria criteria = new Criteria();
        criteria.setValue("value");
        criteria.setId(1L);

        // when
        mapper.updateWith(criteriaEntity, criteria);

        // then
        assertEquals(criteria.getValue(), criteriaEntity.getCriterionValue());
        assertEquals(criteria.getId(), criteriaEntity.getId());
    }
}
