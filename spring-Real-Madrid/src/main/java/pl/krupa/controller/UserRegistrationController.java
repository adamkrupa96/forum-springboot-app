package pl.krupa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.krupa.model.User;
import pl.krupa.service.UserService;
import pl.krupa.validator.UserRegistrationValidator;

@Controller
@RequestMapping("/rejestracja")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationValidator userRegistrationDto() {
        return new UserRegistrationValidator();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "rejestracja";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationValidator userValidator, 
                                      BindingResult result){

        User existing = userService.findByEmail(userValidator.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "Istnieje juz konto o ponadym adresie email!");
        }

        if (result.hasErrors()){
            return "rejestracja";
        }

        userService.save(userValidator);
        return "redirect:/rejestracja?sukces";
    }

}