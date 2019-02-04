package pl.edu.pjwstk.photoapp.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;
import pl.edu.pjwstk.photoapp.domain.users.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {

	List<AppUser> findAll();
	Optional<AppUser> findById(String Id);
	AppUser findByLogin(String login);
	List<AppUser> findByType(Type type);
	AppUser deleteAppUserById(String Id);

}
