package pl.edu.pjwstk.photoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.photoapp.db.repositories.AppUserRepository;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;
import pl.edu.pjwstk.photoapp.domain.users.Type;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

	@Autowired
	private final AppUserRepository repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	AppUserService(AppUserRepository repository) {
		this.repository = repository;
	}

	public List<AppUser> findAllCustomers() {
		return repository.findByType(Type.ROLE_CUSTOMER);
	}

	public List<AppUser> findAll() {
		return repository.findAll();
	}


	public AppUser addCustomer(AppUser appUser) {
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		appUser.setType(Type.ROLE_CUSTOMER);
		return repository.save(appUser);
	}

	public AppUser getByLogin(String login) {
		return repository.findByLogin(login);
	}

	public Optional<AppUser> getById(String id){
		return repository.findById(id);
	}

	public AppUser deleteById(String id){
		return repository.deleteAppUserById(id);
	}

}


