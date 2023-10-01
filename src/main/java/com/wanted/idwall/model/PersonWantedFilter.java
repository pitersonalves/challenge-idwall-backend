package com.wanted.idwall.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table("PERSON_WANTED")
@Builder
public record PersonWantedFilter(@Id Integer id,
                           String name,
                           String image,
                           String description,
                           String caution,
                           String remarks,
                           String scarsMarks,
                           String placeBirth,
                           String nationality,
                           String gender,
                           String height,
                           String weight,
                           String race,
                           String eyes,
                           String base,
                           String baseId,
                           String classificationName) {
}
