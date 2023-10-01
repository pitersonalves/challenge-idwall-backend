package com.wanted.idwall.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record DataViewDTO(List<String> nationality, List<String> classificationName) {
}
