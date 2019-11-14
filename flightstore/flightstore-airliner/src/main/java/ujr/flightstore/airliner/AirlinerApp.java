package ujr.flightstore.airliner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.kubernetes.config.reload.ConfigReloadAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import ujr.flightstore.config.CacheConfigProperties;
import ujr.flightstore.config.MysqlConfigProperties;


@SpringBootApplication(exclude = ConfigReloadAutoConfiguration.class)
@ComponentScan(basePackages = "ujr.flightstore")
@EnableEncryptableProperties
@EnableFeignClients(basePackages = {"ujr.flightstore.airplane.client"})
@EnableConfigurationProperties({MysqlConfigProperties.class, CacheConfigProperties.class})
public class AirlinerApp 
{
    public static void main( String[] args )
    {
    	for(String arg:args) {
    		if ( arg.contains("jasypt.encryptor.password") ) {
    			String jasyptPassword = arg.split("=")[1];
    			System.setProperty("jasypt.encryptor.password", jasyptPassword);
    		} else
    		if ( arg.contains("spring.profiles.active") ) {
    			String profile = arg.split("=")[1];
    			System.setProperty("spring.profiles.active", profile);
    		}
        }
    	
        SpringApplication.run(AirlinerApp.class);
    }
    
}
