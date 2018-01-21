package indexer.api.server.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Crawler crawler;
	
	@Override
	public void addLinks(String link) {
		logger.debug("Adding a new URL to crawler, URL: " + link);
		crawler.addLink(link, 0);
	}

}
