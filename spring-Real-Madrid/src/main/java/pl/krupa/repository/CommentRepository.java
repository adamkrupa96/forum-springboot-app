package pl.krupa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krupa.model.Article;
import pl.krupa.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	public Comment findByCommentId(Long commentId);
	public List<Comment> findByArticle(Article article);
}
