package com.example.filtersystem.context.criteria.infrastructure;

import com.example.filtersystem.context.criteria.domain.CriteriaType;
import com.example.filtersystem.context.filter.infrastructure.FilterEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "criteria")
public class CriteriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filter_id", nullable = false)
    private FilterEntity filter;

    @Enumerated(EnumType.STRING)
    private CriteriaType type;
    private String criterionValue;
    private String condition;
}
