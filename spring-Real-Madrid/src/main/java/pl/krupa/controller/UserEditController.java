package pl.krupa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserEditController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/editUser/{userId}")
	public String editUser(@PathVariable Long userId, @ModelAttribute("edytowanyUzytkownik") @Valid User user,
			BindingResult result) {
		if(result.hasErrors()) 
			return "redirect:/panelAdmina/edytujUzytkownika/" + userId + "?bladEdycji";
		
		userRepository.setUserInfoById(
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				userId
		);
		
		return "redirect:/panelAdmina/listaUzytkownikow?edytowanoUzytkownika";
	}
	
	@GetMapping("/panelAdmina/edytujUzytkownika/{userId}")
	public String stronaEdycji(@PathVariable Long userId, Model model) {
		User user = userRepository.getOne(userId);
		model.addAttribute("edytowanyUzytkownik", user);
		
		return "edytujUzytkownika";
	}

}
