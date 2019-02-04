package pl.edu.pjwstk.photoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.photoapp.db.repositories.UserDataRepository;
import pl.edu.pjwstk.photoapp.domain.users.UserData;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    private final UserDataRepository repository;

    @Autowired
    UserDataService(UserDataRepository repository){
        this.repository = repository;
    }

    public UserData getByEmail(String email){
        return repository.findByEmail(email);
    }

    public UserData addCustomerData(UserData userData){
        return repository.save(userData);
    }

    public List<UserData> findAll(){
        return repository.findAll();
    }

    public Optional<UserData> getById(String id){
        return repository.findById(id);
    }

    public UserData deleteById(String id){
        return repository.deleteUserDataById(id);
    }
}
