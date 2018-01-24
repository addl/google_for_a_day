package indexer.api.server.controller;

import indexer.api.server.dto.IndexerResponseDTO;
import indexer.api.server.exception.IndexerNotMatchesException;
import indexer.api.server.index.BaseIndexer;
import indexer.api.server.index.DatabaseIndexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lion
 * 
 * Exceute search over the index
 * 
 */
@RestController
@RequestMapping(value = "/api/search")
public class SearchRestController {
	
	@Autowired
	private BaseIndexer indexer;
	
	/**
	 * 
	 * Serves a {@link IndexerResponseDTO} as a result of execute a search on {@link DatabaseIndexer}
	 * @param query the word or criteria to be searched
	 * @param page receive parameters such as number, size in a way of get parameters
	 * @return IndexerResponseDTO with data as a {@link Page}(Spring specific data type) of result
	 */
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

