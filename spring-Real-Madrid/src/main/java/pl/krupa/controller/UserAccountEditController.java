package pl.krupa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.krupa.model.User;
import pl.krupa.repository.UserRepository;

@Controller
public class UserAccountEditController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@GetMapping("/kontoUzytkownika")
	public String stronaUzytkownika(Model model) {
		User user = getUserFromSession();
		model.addAttribute("userAccount", user);
		
		return "kontoUzytkownika";
	}
	
	@PostMapping("/editAccount/{userId}") 
	public String edytujKonto(@PathVariable Long userId, @ModelAttribute("userAccount") @Valid User user, BindingResult result) {
		if(!user.getPassword().equals(user.getConfirmPassword()) || user.getPassword() == null) {
			result.rejectValue("password", null, "Hasla musza byc takie same!");
		}
		
		if(result.hasErrors()) {
			return "kontoUzytkownika";
		}
		
		userRepository.setUserInfoByIdAndPassword(
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				passwordEncoder().encode(user.getPassword()), 
				user.getUserId()
		);
		return "redirect:/kontoUzytkownika?sukces";
	}
	
	private User getUserFromSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(userDetail.getUsername());
		return user;
	}
}
