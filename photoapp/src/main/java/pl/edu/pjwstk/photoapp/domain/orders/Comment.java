package pl.edu.pjwstk.photoapp.domain.orders;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Comment {

	@Id
	private String id;
	private AppUser author;
	private LocalDateTime date;
	@NotBlank(message = "Pole nie może być puste")
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AppUser getAuthor() {
		return author;
	}

	public void setAuthor(AppUser author) {
		this.author = author;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
