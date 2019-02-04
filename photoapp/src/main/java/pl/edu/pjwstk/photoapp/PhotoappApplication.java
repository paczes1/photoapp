package pl.edu.pjwstk.photoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.edu.pjwstk.photoapp.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class PhotoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappApplication.class, args);
	}
}
