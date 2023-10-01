package com.wanted.idwall.rest.response.interpol;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record InterpolPersonImage(
        Embedded _embedded,
        Links _links
) {
    public static record Embedded(
            List<Image> images
    ) {
        public static record Image(
                String picture_id,
                ImageLinks _links
        ) {
            public static record ImageLinks(
                    Link self
            ) {
                public static record Link(
                        String href
                ) {}
            }
        }
    }

    public static record Links(
            Link self,
            Link notice,
            Link thumbnail
    ) {
        public static record Link(
                String href
        ) {}
    }
}