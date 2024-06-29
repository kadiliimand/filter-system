package com.example.filtersystem.api.criteria;

import com.example.filtersystem.context.criteria.domain.Criteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CriteriaMapperTest {

    private final CriteriaMapper criteriaMapper = Mappers.getMapper(CriteriaMapper.class);

    @Test
    void shouldMapCriteriaToDto() {
        // given
        Criteria criteria = new Criteria();
        criteria.setValue("value");

        // when
        CriteriaDto criteriaDto = criteriaMapper.toDto(criteria);

        // then
        assertNotNull(criteriaDto);
        assertEquals("value", criteriaDto.getValue());
    }

    @Test
    void shouldMapDtoToCriteria() {
        // given
        CriteriaDto criteriaDto = new CriteriaDto();
        criteriaDto.setCondition("condition");

        // when
        Criteria criteria = criteriaMapper.toDomain(criteriaDto);

        // then
        assertNotNull(criteria);
        assertEquals("condition", criteria.getCondition());
    }
}
