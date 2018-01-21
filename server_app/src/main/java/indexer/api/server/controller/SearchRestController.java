package indexer.api.server.controller;

import indexer.api.server.dto.IndexerResponseDTO;
import indexer.api.server.index.BaseIndexer;
import indexer.api.server.model.Word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/search")
public class SearchRestController {
	
	@Autowired
	private BaseIndexer indexer;
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	public ResponseEntity<IndexerResponseDTO> executeSearch(@RequestParam("query") String query) {
		Word found = (Word) indexer.search(query);
		IndexerResponseDTO response = new IndexerResponseDTO(found, HttpStatus.OK, false);
		return new ResponseEntity<IndexerResponseDTO>(response, HttpStatus.OK);
	}

}
