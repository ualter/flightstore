package ujr.flightstore.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "redis-cache")
@Data
public class CacheConfigProperties {
	
	private long timeoutSeconds = 300;
	private int port = 6379;
	private String host = "127.0.01";
	private Map<String, CacheSpecification> cachesSpecifications = new HashMap<String, CacheSpecification>();
	
	@Data
	public static class CacheSpecification {
		private Long timeout;
	}
	
}