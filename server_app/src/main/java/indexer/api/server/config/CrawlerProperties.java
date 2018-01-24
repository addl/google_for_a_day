package indexer.api.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lion
 * 
 * Get configuration from application.properties file located in classpath root
 */
@Component
@ConfigurationProperties(prefix="crawler")
public class CrawlerProperties {

	private int maximumDeepLevel;
	
	public int getMaximumDeepLevel() {
		return maximumDeepLevel;
	}
	
	public void setMaximumDeepLevel(int maximumDeepLevel) {
		this.maximumDeepLevel = maximumDeepLevel;
	}
}
