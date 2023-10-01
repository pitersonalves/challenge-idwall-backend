package com.wanted.idwall.rest;

import com.wanted.idwall.rest.response.fbi.FBIResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FBIClient {
    private final WebClient webClient;

    public FBIClient(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl("https://api.fbi.gov").build();
    }

    public Mono<FBIResponse> fbiWantedPersonResponseMono(String page) {
        return webClient.get()
                .uri("/wanted/v1/list?page={page}", page)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FBIResponse.class);
    }
}
