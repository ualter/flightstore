package ujr.flightstore.airplane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ujr.flightstore")
public class AirplaneApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(AirplaneApp.class);
    }
}
