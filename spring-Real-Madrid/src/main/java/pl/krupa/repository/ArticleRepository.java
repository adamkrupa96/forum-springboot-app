package pl.krupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.krupa.model.Article;

@Repository
@Transactional(readOnly = true)
public interface ArticleRepository extends JpaRepository<Article, Long>{
	public Article findByArticleId(Long articleId);
	
	@Modifying
	@Query("UPDATE Article a SET a.title = ?1, a.content = ?2, a.source = ?3 WHERE a.id = ?4")
	@Transactional
	public void setArticleInfoById(String title, String content, String source, Long articleId);
	
	@Modifying
    @Transactional
    @Query("delete from Article a where a.articleId = ?1")
    public void deleteArticleByArticleId(Long articleId);
}
