package pl.edu.pjwstk.photoapp.domain.reservations;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;
import pl.edu.pjwstk.photoapp.domain.users.UserData;

import java.time.LocalDateTime;

public class Reservation {

	@Id
	private String id;
	@DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
	private LocalDateTime date;
	private AppUser customer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public AppUser getCustomer() {
		return customer;
	}

	public void setCustomer(AppUser customer) {
		this.customer = customer;
	}
}
