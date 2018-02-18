package pl.krupa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.krupa.model.User;
import pl.krupa.repository.UserRepository;

@Controller
@RequestMapping("/panelAdmina")
public class AdminPanelController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public String wyswietlPanel() {
		return "panelAdmina";
	}
	
	@GetMapping("/listaUzytkownikow")
	public String wyswietlUzytkownikow(Model model) {
		List<User> usersList = userRepository.findAll();
		model.addAttribute("listaUzytkownikow", usersList);
		return "listaUzytkownikowPanelAdmina";
	}
}
