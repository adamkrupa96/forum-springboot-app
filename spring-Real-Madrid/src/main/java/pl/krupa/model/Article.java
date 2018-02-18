package pl.krupa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_artykulu")
	private Long articleId;
	
	@Column(name = "tytul")
	@NotEmpty
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "utworzony")
	private Date createdOn;
	
	@Column(name = "zawartosc_tekstowa", length = 2048)
	@NotEmpty
	private String content;
	
	@Column(name = "zrodlo")
	@NotEmpty
	private String source;
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Comment> comments;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User author;

	public Article() {
	}

	public Article(String title, String content, User user) {
		this.title = title;
		this.createdOn = new Date();
		this.content = content;
		this.author = user;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
