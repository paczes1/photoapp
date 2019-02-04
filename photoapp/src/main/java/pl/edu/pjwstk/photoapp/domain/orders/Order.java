package pl.edu.pjwstk.photoapp.domain.orders;

import org.springframework.data.annotation.Id;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

	@Id
	private String id;
	private AppUser employee;
	private AppUser customer;
	private LocalDateTime date;
	private LocalDateTime lastUpdate;
	private List<String> imageUrls;
	private List<Comment> comments;
	private OrderStatus status;

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

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public AppUser getEmployee() {
		return employee;
	}

	public void setEmployee(AppUser employee) {
		this.employee = employee;
	}

	public AppUser getCustomer() {
		return customer;
	}

	public void setCustomer(AppUser customer) {
		this.customer = customer;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}
