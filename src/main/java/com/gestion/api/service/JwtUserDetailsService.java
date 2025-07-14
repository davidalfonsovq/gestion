package com.gestion.api.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The Class JwtUserDetailsService.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//		pass encriptada $2a$12$aUPavebUrkdTctnxpfzJ8..9qsWVUvHsoiqSQcOZ50aGjq6okE5nG con Bcrypt
		//		es 1234 para cambiar usar alguna pagina que permita ecriptar en Bcrypt
		if ("admin".equals(username)) {
			return new User("admin", "$2a$12$xEY7y4HDJoK1MMo/IFsXIuNJrOwfxZqWQ/rkXXGiWjaakRxuDb1k6",new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}