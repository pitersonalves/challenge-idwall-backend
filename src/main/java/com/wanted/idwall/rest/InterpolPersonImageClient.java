package com.wanted.idwall.rest;

import com.wanted.idwall.rest.response.interpol.InterpolPersonImage;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InterpolPersonImageClient {
    private final WebClient webClient;

    public InterpolPersonImageClient(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl("https://ws-public.interpol.int").build();
    }

    public Mono<InterpolPersonImage> getImageOfPersonInformationWantedFull(String id) {
        return webClient.get()
                .uri("/notices/v1/red/{id}/images", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(InterpolPersonImage.class);
    }
}
