package indexer.api.server.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the word database table.
 * 
 */
@Entity
@NamedQuery(name="Word.findAll", query="SELECT w FROM Word w")
public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_word", sequenceName="seq_word")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_word")
	private long id;

	private String lexeme;

	//bi-directional many-to-one association to WebPage
	@OneToMany(mappedBy="word", cascade=CascadeType.ALL)
	private List<WebPage> webPages;

	public Word() {
		webPages = new LinkedList<WebPage>();
	}
	
	public Word(String lexeme) {
		this.lexeme = lexeme;
		webPages = new LinkedList<WebPage>();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLexeme() {
		return this.lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public List<WebPage> getWebPages() {
		return this.webPages;
	}

	public void setWebPages(List<WebPage> webPages) {
		this.webPages = webPages;
	}

	public WebPage addWebPage(WebPage webPage) {
		getWebPages().add(webPage);
		webPage.setWord(this);
		return webPage;
	}

	public WebPage removeWebPage(WebPage webPage) {
		getWebPages().remove(webPage);
		webPage.setWord(null);

		return webPage;
	}

}