package pl.krupa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String glowna() {
		return "index";
	}
	
	@GetMapping("/zaloguj")
	public String zaloguj() {
		return "zaloguj";
	}
}
