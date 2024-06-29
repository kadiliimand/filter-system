package com.example.filtersystem.context.filter.infrastructure;

import com.example.filtersystem.context.criteria.infrastructure.CriteriaEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "filter")
public class FilterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer filterSequence;
    private String title;

    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CriteriaEntity> criteriaList;
}
