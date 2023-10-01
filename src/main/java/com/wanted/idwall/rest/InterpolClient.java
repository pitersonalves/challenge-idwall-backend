package com.wanted.idwall.rest;

import com.wanted.idwall.rest.response.interpol.InterpolResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InterpolClient {
    private final WebClient webClient;

    public InterpolClient(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl("https://ws-public.interpol.int").build();
    }

    public Mono<InterpolResponse> getPeopleWanted() {
        return webClient.get()
                .uri("/notices/v1/red?resultPerPage=160")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(InterpolResponse.class);
    }
}
