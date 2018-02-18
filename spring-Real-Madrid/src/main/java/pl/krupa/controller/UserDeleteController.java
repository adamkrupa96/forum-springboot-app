package pl.krupa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.krupa.model.Role;
import pl.krupa.model.User;
import pl.krupa.repository.RoleRepository;
import pl.krupa.repository.UserRepository;

@Controller
public class UserDeleteController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/panelAdmina/usunUzytkownika/{userId}")
	public String stronaUsun(@PathVariable Long userId, Model model) {
		User user = userRepository.findByUserId(userId);
		model.addAttribute("userToDelete", user);
		return "usunUzytkownika";
	}
	
	@PostMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		User userAdmin = userRepository.getOne(userId);
		Role admin = roleRepository.findByName("ROLE_ADMIN");
		if(userAdmin.getRoles().contains(admin)) {
			return "redirect:/panelAdmina/listaUzytkownikow?bladUsuwania";
		}
		
		userRepository.delete(userId);
		
		return "redirect:/panelAdmina/listaUzytkownikow?usunieto";
	}
}
