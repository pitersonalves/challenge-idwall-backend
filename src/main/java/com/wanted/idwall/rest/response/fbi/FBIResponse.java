package com.wanted.idwall.rest.response.fbi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record FBIResponse (Integer total,
                           java.util.List<FBIWantedPersonResponse> items,
                           Integer page) {
}
