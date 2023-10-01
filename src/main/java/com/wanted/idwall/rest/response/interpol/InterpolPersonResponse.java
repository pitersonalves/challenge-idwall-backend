package com.wanted.idwall.rest.response.interpol;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record InterpolPersonResponse(
        String date_of_birth,
        String distinguishing_marks,
        double weight,
        List<String> nationalities,
        String entity_id,
        List<String> eyes_colors_id,
        String sex_id,
        String place_of_birth,
        String forename,
        List<ArrestWarrant> arrest_warrants,
        String country_of_birth_id,
        List<String> hairs_id,
        String name,
        List<String> languages_spoken_ids,
        double height,
        Embedded _embedded,
        Links _links
) {
    public static record ArrestWarrant(
            String charge,
            String issuing_country_id,
            String charge_translation
    ) {}

    public static record Embedded(
            List<Link> links
    ) {
        public static record Link(
                String href
        ) {}
    }

    public static record Links(
            Link self,
            Link images,
            Link thumbnail
    ) {
        public static record Link(
                String href
        ) {}
    }
}
