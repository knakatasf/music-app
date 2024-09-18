package music_app.music_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MusicAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicAppBackendApplication.class, args);
	}
}
