package pl.edu.pjwstk.photoapp.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.photoapp.domain.users.UserData;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {

    UserData findByEmail(String email);
    List<UserData> findAll();
    Optional<UserData> findById(String Id);
    UserData deleteUserDataById(String Id);
}
