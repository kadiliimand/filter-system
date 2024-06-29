package com.example.filtersystem.api.criteria;

import com.example.filtersystem.context.criteria.domain.CriteriaType;
import com.example.filtersystem.validation.ValidText;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CriteriaDto {

    private Long id;
    @NotNull
    private Long filterId;
    @NotNull
    @ValidText
    private CriteriaType type;
    @NotNull
    @ValidText
    private String condition;
    @NotNull
    @ValidText
    private String value;
}
