package indexer.api.server.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskExecutor taskExecutor;

	@Autowired
	private Crawler crawler;

	@Override
	public void addLinkAsynchronously(String link) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				logger.info("Adding Link: {} Asynchronously", link);
				crawler.addLink(link, 0);
			}
		});
	}

	@Override
	public void addLinks(String link) {
		logger.debug("Adding a new URL to crawler, URL: " + link);
		crawler.addLink(link, 0);
	}

}
