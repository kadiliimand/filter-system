package com.example.filtersystem.api.filter;

import com.example.filtersystem.api.criteria.CriteriaDto;
import com.example.filtersystem.validation.ValidText;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FilterDto {

    private Long id;
    @NotNull
    private Integer sequence;
    @NotNull
    @ValidText
    private String title;
    private List<CriteriaDto> criteriaList;
}
