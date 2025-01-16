package service.shuffle.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import service.shuffle.client.LogHttpClient;

class LogServiceTest {


    private LogHttpClient logHttpClient;

    @Mock
    private WebClient webClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void sendLogRequest_shouldSendHttpRequest_whenCalled() {
        // TODO: Placeholder for implementation due to time constraints. To be implemented later.
    }

    @Test
    void fallbackLogRequest_shouldLogError_whenRetryFails() {
        // TODO: Placeholder for implementation due to time constraints. To be implemented later.
    }
}