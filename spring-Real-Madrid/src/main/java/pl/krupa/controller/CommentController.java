package pl.krupa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@PostMapping("/addComment/{articleId}")
	public String dodajKomentarz(@PathVariable Long articleId, @ModelAttribute @Valid Comment comment, BindingResult result) {
        if (result.hasErrors()) {
        	return "redirect:/aktualnosci/" + articleId + "?blad";
        }
		
		User author = getUserFromSession();
        
        Article article = articleRepository.findByArticleId(articleId);
        comment.setArticle(article);
        comment.setAuthor(author);
        commentRepository.save(comment);
        
		return "redirect:/aktualnosci/" + articleId;
	}
	
	private User getUserFromSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(userDetail.getUsername());
		return user;
	}
}
