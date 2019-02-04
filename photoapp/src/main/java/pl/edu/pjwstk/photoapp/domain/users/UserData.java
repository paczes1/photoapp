package pl.edu.pjwstk.photoapp.domain.users;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

public class UserData {

	@Id
	private String id;

	@NotEmpty(message = "Pole nie może być puste")
	private String firstName;
	@NotEmpty(message = "Pole nie może być puste")
	private String lastName;
	@NotEmpty(message = "Pole nie może być puste")
	private String phoneNumber;
	@NotEmpty(message = "Pole nie może być puste")
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
