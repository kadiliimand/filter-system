package com.example.filtersystem.context.criteria.domain;

import lombok.Data;

@Data
public class Criteria {

    private Long id;
    private Long filterId;
    private CriteriaType type;
    private String condition;
    private String value;
}
