package pl.krupa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_komentarza")
	private Long commentId;

	@Column(name = "zawartosc", length = 2048)
	@NotEmpty
	private String content;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_artykulu")
	private Article article;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "utworzony")
	private Date createdOn;

	public Comment() {
	}

	public Comment(String content, User author, Article article) {
		this.content = content;
		this.author = author;
		this.createdOn = new Date();
		this.article = article;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
