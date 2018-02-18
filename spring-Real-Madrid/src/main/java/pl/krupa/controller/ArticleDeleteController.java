package pl.krupa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.krupa.model.Article;
import pl.krupa.repository.ArticleRepository;

@Controller
public class ArticleDeleteController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/panelAdmina/usunArtykul/{articleId}")
	public String stronaUsunArtykul(@PathVariable Long articleId, Model model) {
		Article article = articleRepository.findByArticleId(articleId);
		model.addAttribute("articleToDelete", article);
		
		return "usunArtykul";
	}
	
	@PostMapping("/deleteArticle/{articleId}")
	public String usunArtykul(@PathVariable Long articleId) {
		articleRepository.delete(articleRepository.findByArticleId(articleId));
		
		return "redirect:/panelAdmina/listaArtykulow?usunieto";
	}
}
