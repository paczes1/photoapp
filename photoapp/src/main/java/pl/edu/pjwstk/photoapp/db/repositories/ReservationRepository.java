package pl.edu.pjwstk.photoapp.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.photoapp.domain.orders.Order;
import pl.edu.pjwstk.photoapp.domain.reservations.Reservation;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

	List<Reservation> findByDate(LocalDateTime date);

	Optional<Reservation> findById(String id);
	List<Reservation> findByCustomer(AppUser customer);
}
