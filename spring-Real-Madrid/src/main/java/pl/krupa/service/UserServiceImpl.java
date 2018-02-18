package pl.krupa.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.krupa.model.Role;
import pl.krupa.model.User;
import pl.krupa.repository.UserRepository;
import pl.krupa.validator.UserRegistrationValidator;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Niepoprawna nazwa uzytkownika lub haslo");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(UserRegistrationValidator userValidator) {
		User user = new User();
		user.setFirstName(userValidator.getFirstName());
		user.setLastName(userValidator.getLastName());
		user.setEmail(userValidator.getEmail());
		user.setPassword(passwordEncoder.encode(userValidator.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		userRepository.save(user);
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	
}
