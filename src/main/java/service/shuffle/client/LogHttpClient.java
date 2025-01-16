package service.shuffle.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import service.shuffle.dto.ShuffleLogRequest;

@Component
public class LogHttpClient {

    private final WebClient webClient;

    public LogHttpClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<Void> sendLogRequest(String url, ShuffleLogRequest logRequest) {
        return webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(logRequest)
                .retrieve()
                .bodyToMono(Void.class);
    }
}