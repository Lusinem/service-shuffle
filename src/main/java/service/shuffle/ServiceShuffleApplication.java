package service.shuffle;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import service.shuffle.config.ShuffleNumberProperties;
import service.shuffle.utils.ValidationUtils;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Shuffle Service API", version = "1.0.0", description = "API for shuffling numbers"))
@EnableConfigurationProperties(ShuffleNumberProperties.class)
public class ServiceShuffleApplication {
	private final ShuffleNumberProperties shuffleNumberProperties;

	public ServiceShuffleApplication(ShuffleNumberProperties shuffleNumberProperties) {
		this.shuffleNumberProperties = shuffleNumberProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceShuffleApplication.class, args);
	}

	@PostConstruct
	public void init() {
		ValidationUtils.initialize(shuffleNumberProperties);
	}
}
