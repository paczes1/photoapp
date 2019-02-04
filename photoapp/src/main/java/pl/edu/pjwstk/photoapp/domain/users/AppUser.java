package pl.edu.pjwstk.photoapp.domain.users;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AppUser {

	@Id
	private String id;
	@NotNull(message = "Pole nie może być puste")
	@Size(min = 3, max = 32, message = "Login musi się składać z minimum 3 znaków, a maksymalnie 32")
	private String login;
	@Size(min =3, max=32, message = "Hasło musi się składać z minimum 3 znaków, a maksymalnie 32")
	private String password;
	private UserData userData;
	private Type type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "AppUser{" +
				", login='" + login + '\'' +
				", type=" + type +
				'}';
	}
}
