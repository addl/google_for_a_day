package indexer.api.server.controller;

import indexer.api.server.crawler.CrawlerService;
import indexer.api.server.dto.IndexerResponseDTO;
import indexer.api.server.dto.IndexerUrlDTO;
import indexer.api.server.index.BaseIndexer;

import javax.validation.Valid;

import org.apache.commons.validator.routines.UrlValidator;
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
		UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
		if(urlValidator.isValid(urlDto.getUrl())){
			//crawlerService.addLinks(urlDto.getUrl());
			crawlerService.addLinkAsynchronously(urlDto.getUrl());
			IndexerResponseDTO response = new IndexerResponseDTO(null, HttpStatus.OK, false);
			return new ResponseEntity<IndexerResponseDTO>(response, HttpStatus.OK);
		}else{
			IndexerResponseDTO errorResponse = new IndexerResponseDTO(null, HttpStatus.BAD_REQUEST, true, "URL format is incorrect", null);
			return new ResponseEntity<IndexerResponseDTO>(errorResponse, HttpStatus.BAD_REQUEST);
		}
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
	
	@RequestMapping(value="/stats", method=RequestMethod.GET)
	public ResponseEntity<IndexerResponseDTO> getStats(){
		IndexerResponseDTO response = new IndexerResponseDTO(indexer.getStats(), HttpStatus.OK, false);
		return new ResponseEntity<IndexerResponseDTO>(response, HttpStatus.OK);
	}

}
