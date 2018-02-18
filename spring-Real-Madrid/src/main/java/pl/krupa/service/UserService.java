package pl.krupa.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import pl.krupa.model.User;
import pl.krupa.validator.UserRegistrationValidator;

public interface UserService extends UserDetailsService {
	public User findByEmail(String email);
	public void save(UserRegistrationValidator userValidator);
}
