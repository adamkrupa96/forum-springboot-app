package pl.krupa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.krupa.model.Article;
import pl.krupa.repository.ArticleRepository;

@Controller
public class ArticleEditController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/panelAdmina/edytujArtykul/{articleId}")
	public String stronaEdycji(@PathVariable Long articleId, Model model) {
		Article article = articleRepository.findByArticleId(articleId);
		model.addAttribute("edytowanyArtykul", article);
		
		return "edytujArtykul";
	}
	
	@PostMapping("/editArticle/{articleId}")
	public String edytujArtykul(@PathVariable Long articleId, @ModelAttribute("edytowanyArtykul") @Valid Article article, BindingResult result) {
		if(result.hasErrors()) 
			return "redirect:/panelAdmina/edytujArtykul/" + articleId + "?bladEdycji";
		
		articleRepository.setArticleInfoById(
				article.getTitle(), 
				article.getContent(), 
				article.getSource(), 
				articleId
	);
		return "redirect:/panelAdmina/listaArtykulow?edytowano";
	}
	
	@GetMapping("/panelAdmina/listaArtykulow")
	public String listaArtykulow(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("listaArtykulow", articleList);
		
		return "listaArtykulowPanelAdmina";
	}
}
