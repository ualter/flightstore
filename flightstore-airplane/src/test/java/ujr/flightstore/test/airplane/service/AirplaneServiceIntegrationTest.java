package ujr.flightstore.test.airplane.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ujr.flightstore.airplane.service.AirplaneService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AirplaneServiceIntegrationTest {
	
	@Autowired
	private AirplaneService airplaneService;
	
	
	@Test
	public void testList() {
		assertThat(airplaneService.list())
			.isNotNull();
			//.allMatch(p -> p.getManufacturer().getName().toLowerCase().equals("airbus") );
	}                
}
