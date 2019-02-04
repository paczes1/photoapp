package pl.edu.pjwstk.photoapp.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.photoapp.domain.orders.Order;
import pl.edu.pjwstk.photoapp.domain.orders.OrderStatus;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findOrderByStatus(OrderStatus status);
	List<Order> findByCustomer(AppUser customer);
	List<Order> findByDate(LocalDateTime dateTime);
	Optional<Order> findById(String id);

}
