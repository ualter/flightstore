package ujr.flightstore.airplane.client;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.Request;
import feign.Retryer;

public class FeignConfiguration {
	
	@Bean
	public Logger.Level configureLogLevel() {
		return Logger.Level.FULL;
	}
	
	@Bean
	public Request.Options timeoutConfiguration() {
		return new Request.Options(5000, 3000);
	}
	
	@Bean
	public Retryer retryer() {
	  return new Retryer.Default(1000, 8000, 3);    
	}

}
