package indexer.api.server.crawler;

import indexer.api.server.exception.UrlBadFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author lion
 *
 *The CrawlerServiceImpl is an interface to allow other components communicate with Crawler component
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskExecutor taskExecutor;

	@Autowired
	private Crawler crawler;

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.CrawlerService#addLinkAsynchronously(java.lang.String)
	 */
	@Override
	public void addLinkAsynchronously(String link) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				logger.info("Adding Link: {} Asynchronously", link);
				try {
					crawler.addLink(link, 0);
				} catch (UrlBadFormatException e) {
					logger.error("Can't process link: {}, Error is: {}", link, e.getMessage());
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.CrawlerService#addLinks(java.lang.String)
	 */
	@Override
	public void addLinks(String link) throws UrlBadFormatException {
		logger.debug("Adding a new URL to crawler, URL: " + link);
		crawler.addLink(link, 0);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.CrawlerService#resetCrawler()
	 */
	@Override
	public void resetCrawler() {
		logger.debug("Resetting crawler");
		crawler.resetCrawler();
	}

}
