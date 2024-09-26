package music_app.music_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "music_app.music_app_backend.entity")
public class MusicAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicAppBackendApplication.class, args);
	}
}
