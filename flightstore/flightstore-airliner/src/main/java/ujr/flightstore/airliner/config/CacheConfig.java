package ujr.flightstore.airliner.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import lombok.extern.slf4j.Slf4j;
import ujr.flightstore.config.CacheConfigProperties;
import ujr.flightstore.config.CacheConfigProperties.CacheSpecification;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {


	private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
		return RedisCacheConfiguration
				.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(timeoutInSeconds));
	}
	
	@Bean
    public LettuceConnectionFactory redisConnectionFactory(CacheConfigProperties properties) {
		log.info("===> REDIS configuration enabled. Cache timeout {} seconds.", properties.getTimeoutSeconds());
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setHostName(properties.getHost());
		redisConfig.setPort(properties.getPort());
		return new LettuceConnectionFactory(redisConfig);
	}
	
	@Bean
    public RedisCacheConfiguration cacheConfiguration(CacheConfigProperties properties) {
		return createCacheConfiguration(properties.getTimeoutSeconds());
	}
	
	@Bean(name="cacheManager")
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, CacheConfigProperties properties) {
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		
		for(Entry<String,CacheSpecification> cacheAndTimeOut : properties.getCachesSpecifications().entrySet()) {
			log.info("==== REDIS CONFIG...: {} - {}",cacheAndTimeOut.getKey(), cacheAndTimeOut.getValue());
			log.info(cacheAndTimeOut.getKey());
			cacheConfigurations.put(cacheAndTimeOut.getKey(), createCacheConfiguration(cacheAndTimeOut.getValue().getTimeout()));
		}
		
		return RedisCacheManager
				.builder(redisConnectionFactory)
				.cacheDefaults(cacheConfiguration(properties))
				.withInitialCacheConfigurations(cacheConfigurations).build();
	}



}
