package pl.edu.pjwstk.photoapp.service.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void storeFile(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadFileAsResource(String filename);

	void deleteAll();

}
