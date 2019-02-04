package pl.edu.pjwstk.photoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.photoapp.db.repositories.OrderRepository;
import pl.edu.pjwstk.photoapp.domain.orders.Order;
import pl.edu.pjwstk.photoapp.domain.orders.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final AppUserService appUserService;

	@Autowired
	OrderService(OrderRepository orderRepository, AppUserService appUserService) {
		this.orderRepository = orderRepository;
		this.appUserService = appUserService;
	}

	public String createNewOrder(Order order) {
		order.setStatus(OrderStatus.NEW);
		order.setDate(LocalDateTime.now());
		return orderRepository.save(order).getId();
	}

	public Optional<Order> getById(String id) {
		return orderRepository.findById(id);
	}

	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}

	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}

	public List<Order> getByCustomer(String username) {

		return orderRepository.findByCustomer(appUserService.getByLogin(username));
	}

	public List<Order> getByStatus(OrderStatus status) {
		return orderRepository.findOrderByStatus(status);
	}
}
