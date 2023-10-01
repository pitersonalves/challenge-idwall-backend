package com.wanted.idwall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PersonWantedDTO(Integer id,
                              String name,
                              String image,
                              String description,
                              String caution,
                              String remarks,
                              @JsonProperty("scars_marks")
                              String scarsMarks,
                              @JsonProperty("place_birth")
                              String placeBirth,
                              String nationality,
                              String gender,
                              String height,
                              String weight,
                              String race,
                              String eyes,
                              String base,
                              @JsonProperty("base_id")
                              String baseId,
                              @JsonProperty("classification_name")
                              String classificationName) {

}
