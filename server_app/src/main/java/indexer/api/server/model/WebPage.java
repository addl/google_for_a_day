package indexer.api.server.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the web_page database table.
 * 
 */
@Entity
@Table(name="web_page")
@NamedQuery(name="WebPage.findAll", query="SELECT w FROM WebPage w")
public class WebPage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_web_page", sequenceName="seq_web_page")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_web_page")
	private long id;

	private Integer matches;

	private String url;
	
	private String title;

	//bi-directional many-to-one association to Word
	@ManyToOne
	@JoinColumn(name="wordid")
	@JsonIgnore
	private Word word;

	public WebPage() {
	}

	public WebPage(Integer matches, String url, String title) {
		this.matches = matches;
		this.url = url;
		this.title = title;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getMatches() {
		return this.matches;
	}

	public void setMatches(Integer matches) {
		this.matches = matches;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Word getWord() {
		return this.word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

}