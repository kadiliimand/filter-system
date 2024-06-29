package com.example.filtersystem.api.filter;

import com.example.filtersystem.api.criteria.CriteriaMapper;
import com.example.filtersystem.context.filter.domain.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filters")
@RequiredArgsConstructor
@Validated
public class FilterController {

    private final FilterService filterService;
    private final FilterMapper filterMapper;

    /* All endpoints should include authorization and validation of all incoming values */

    @GetMapping
    public List<FilterDto> getAllFilters() {
        return filterService.getAllFilters().stream()
                .map(filterMapper::toDto)
                .toList();
    }

    @GetMapping("/sequence/{sequence}")
    public FilterDto getFilterBySequence(@PathVariable Integer sequence) {
        return filterMapper.toDto(filterService.getFilterByFilterSequence(sequence));
    }

    @PostMapping
    public ResponseEntity<FilterDto> saveFilter(@Valid @RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(filterMapper.toDto(filterService.saveFilter(filterMapper.toDomain(filterDto))));
    }

    @PutMapping
    public ResponseEntity<FilterDto> updateFilter(@Valid @RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(filterMapper.toDto(filterService.saveFilter(filterMapper.toDomain(filterDto))));
    }

    @DeleteMapping("/{id}")
    public void deleteFilter(@PathVariable Long id) {
        filterService.deleteFilter(id);
    }
}
