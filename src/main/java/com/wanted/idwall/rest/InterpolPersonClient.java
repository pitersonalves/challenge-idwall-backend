package com.wanted.idwall.rest;

import com.wanted.idwall.rest.response.interpol.InterpolPersonResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InterpolPersonClient {
    private final WebClient webClient;

    public InterpolPersonClient(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl("https://ws-public.interpol.int").build();
    }

    public Mono<InterpolPersonResponse> getPersonInformationWantedFull(String id) {
        return webClient.get()
                .uri("/notices/v1/red/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(InterpolPersonResponse.class);
    }
}
