package ujr.flightstore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix="mysql")
@Data
public class MysqlConfigProperties {
	
	private String ip;
	private long port;

}
