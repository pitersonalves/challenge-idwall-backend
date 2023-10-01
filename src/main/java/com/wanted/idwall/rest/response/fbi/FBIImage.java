package com.wanted.idwall.rest.response.fbi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record FBIImage(String original,
                       String large,
                       String thumb,
                       String caption) {
}
