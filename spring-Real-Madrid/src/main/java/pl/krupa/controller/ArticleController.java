package pl.krupa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.krupa.model.Article;
import pl.krupa.model.Comment;
import pl.krupa.model.User;
import pl.krupa.repository.ArticleRepository;
import pl.krupa.repository.CommentRepository;
import pl.krupa.repository.UserRepository;

@Controller
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping("/aktualnosci")
	public String stronaAktualnosci(Model model) {
		List<Article> aktualnosci = articleRepository.findAll();
		model.addAttribute("allNews", aktualnosci);
		return "aktualnosci";
	}
	
	@PostMapping("/saveArticle")
	public String zapiszArtykul(@ModelAttribute @Valid Article article, BindingResult result) {
        if(result.hasErrors()) 
        	return "redirect:/panelAdmina/dodajArtykul?bladDodawania";
		
		User user = getUserFromSession();
        
		article.setAuthor(user);
		articleRepository.save(article);
		return "redirect:/panelAdmina/dodajArtykul?sukces";
	}
	
	@GetMapping("/panelAdmina/dodajArtykul")
	public String dodajArtykul(Model model) {
		model.addAttribute("artykul", new Article());
		return "dodajArtykul";
	}
	
	@GetMapping("/aktualnosci/{id}")
	public String pojedynczyArtykul(@PathVariable Long id, Model model, Authentication authentication) {
		Article artykul = articleRepository.findByArticleId(id);
		List<Comment> comments = commentRepository.findByArticle(artykul);
		
		if (isLoggedIn(authentication)) {
			User onlineUser = getUserFromSession();
			String userName = onlineUser.getFirstName() + " " + onlineUser.getLastName();
			model.addAttribute("userOnlineName", userName);
		}
		
		model.addAttribute("allComments", comments);
		model.addAttribute("artykul", artykul);
		model.addAttribute("comment", new Comment());
		return "pojedynczyNews";
	}
	
	private User getUserFromSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(userDetail.getUsername());
		return user;
	}
	
	private boolean isLoggedIn(Authentication authentication) {
	    return (authentication != null) && !(authentication instanceof AnonymousAuthenticationToken);
	}
}
