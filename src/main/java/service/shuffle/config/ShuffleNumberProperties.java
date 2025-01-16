package service.shuffle.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "shuffle.number")
public class ShuffleNumberProperties {
    private int min;
    private int max;
    private String url;
}