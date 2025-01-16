package service.shuffle.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import service.shuffle.client.LogHttpClient;
import service.shuffle.config.ShuffleNumberProperties;
import service.shuffle.dto.ShuffleLogRequest;

@Service
public class LogService {

    private static final Logger logger = LogManager.getLogger(LogService.class);
    private final LogHttpClient logHttpClient;
    private final ShuffleNumberProperties shuffleNumberProperties;

    public LogService(final LogHttpClient logHttpClient, final ShuffleNumberProperties shuffleNumberProperties) {
        this.logHttpClient = logHttpClient;
        this.shuffleNumberProperties = shuffleNumberProperties;
    }

    @Retry(name = "serviceLogRetry", fallbackMethod = "fallbackLogRequest")
    public void sendLogRequest(final Integer number, final int[] shuffledArray) {
        ShuffleLogRequest logRequest = new ShuffleLogRequest(number, shuffledArray);
        logHttpClient.sendLogRequest(shuffleNumberProperties.getUrl(), logRequest)
                .doOnError(e -> logger.error("Failed to send log request: {}", e.getMessage()))
                .subscribe();
    }

    private void fallbackLogRequest(final Integer number, final int[] shuffledArray, final Throwable throwable) {
        logger.error("Fallback executed: Unable to send log request for number [{}]. Error: {}", number, throwable.getMessage());
    }
}