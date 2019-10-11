package ujr.flightstore.airplane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import ujr.flightstore.config.CacheConfigProperties;
import ujr.flightstore.config.MysqlConfigProperties;

@SpringBootApplication
@ComponentScan(basePackages = "ujr.flightstore")
@EnableEncryptableProperties
@EnableConfigurationProperties({MysqlConfigProperties.class, CacheConfigProperties.class})
public class AirplaneApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(AirplaneApp.class);
    }
}
