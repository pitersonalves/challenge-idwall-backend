package com.wanted.idwall.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("WANTED_CLASSIFICATION")
@Builder
public record WantedClassification(@Id Integer id,
                                   @Column("CLASSIFICATION_NAME")
                                   String classificationName,
                                   @Column("PERSON_WANTED_ID")
                                   Integer personWantedId) {
}
