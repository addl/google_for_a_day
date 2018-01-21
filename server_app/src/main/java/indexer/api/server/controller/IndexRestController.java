package indexer.api.server.controller;

import indexer.api.server.crawler.CrawlerService;
import indexer.api.server.dto.IndexerResponseDTO;
import indexer.api.server.dto.IndexerUrlDTO;
import indexer.api.server.index.BaseIndexer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/index")
public class IndexRestController {

	@Autowired
	private BaseIndexer indexer;
	
	@Autowired
	private CrawlerService crawlerService;

	@RequestMapping(value = "/add/url", method = RequestMethod.POST)
	public ResponseEntity<IndexerResponseDTO> addURL(
			@Valid @RequestBody IndexerUrlDTO urlDto) {
		crawlerService.addLinks(urlDto.getUrl());
		IndexerResponseDTO response = new IndexerResponseDTO(null, HttpStatus.OK, false);
		return new ResponseEntity<IndexerResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ResponseEntity<IndexerResponseDTO> clearIndex() {
		try {
			indexer.clearIndex();
			IndexerResponseDTO response = new IndexerResponseDTO(null, HttpStatus.OK, false);
			return new ResponseEntity<IndexerResponseDTO>(response, HttpStatus.OK);
		} catch (Exception e) {
			IndexerResponseDTO error = new IndexerResponseDTO(null,	HttpStatus.INTERNAL_SERVER_ERROR, true, 
					e.getMessage(),	"Internal Server Error");
			return new ResponseEntity<IndexerResponseDTO>(error, HttpStatus.OK);
		}

	}

}
