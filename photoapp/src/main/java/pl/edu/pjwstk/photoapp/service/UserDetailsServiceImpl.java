package pl.edu.pjwstk.photoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.photoapp.db.repositories.AppUserRepository;
import pl.edu.pjwstk.photoapp.domain.users.AppUser;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		AppUser appUser = userRepository.findByLogin(login);
		if (appUser == null) {
			throw new UsernameNotFoundException(login);
		}
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUser.getType().toString());
		return new User(appUser.getLogin(), appUser.getPassword(), Collections.singletonList(authority));
	}

}

