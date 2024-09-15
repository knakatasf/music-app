package music_app.music_app_backend;

import music_app.music_app_backend.llm.LLMService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicAppBackendApplication.class, args);
	}

}
