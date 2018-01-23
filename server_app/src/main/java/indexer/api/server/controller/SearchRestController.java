package indexer.api.server.controller;

import indexer.api.server.dto.IndexerResponseDTO;
import indexer.api.server.exception.IndexerNotMatchesException;
import indexer.api.server.index.BaseIndexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/search")
public class SearchRestController {
	
	@Autowired
	private BaseIndexer indexer;
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public IndexerResponseDTO executeSearch(@RequestParam("query") String query, Pageable page) {
		IndexerResponseDTO response;
		try {
			response = new IndexerResponseDTO(indexer.search(query, page), HttpStatus.OK, false);
		} catch (IndexerNotMatchesException e) {
			response = new IndexerResponseDTO(null, HttpStatus.NO_CONTENT, false, e.getMessage(), "No explanation");
		}
		return response;
	}

}

