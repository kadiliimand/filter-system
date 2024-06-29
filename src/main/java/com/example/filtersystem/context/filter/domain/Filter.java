package com.example.filtersystem.context.filter.domain;

import com.example.filtersystem.context.criteria.domain.Criteria;
import lombok.Data;

import java.util.List;

@Data
public class Filter {

        private Long id;
        private Integer sequence;
        private String title;
        private List<Criteria> criteriaList;
}
