package ujr.flightstore.airplane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@ComponentScan(basePackages = "ujr.flightstore")
@EnableEncryptableProperties
public class AirplaneApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(AirplaneApp.class);
    }
}
