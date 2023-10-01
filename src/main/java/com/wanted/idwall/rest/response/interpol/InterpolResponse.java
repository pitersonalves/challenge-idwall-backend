package com.wanted.idwall.rest.response.interpol;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record InterpolResponse(
        int total,
        Query query,
        Embedded _embedded,
        Links _links
) {
    public static record Query(
            int page,
            int resultPerPage
    ) {}

    public static record Embedded(
            List<Notice> notices
    ) {}

    public static record Notice(
            String date_of_birth,
            List<String> nationalities,
            String entity_id,
            String forename,
            String name,
            Links _links
    ) {
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

    public static record Links(
            Link self,
            Link first,
            Link next,
            Link last
    ) {
        public static record Link(
                String href
        ) {}
    }
}