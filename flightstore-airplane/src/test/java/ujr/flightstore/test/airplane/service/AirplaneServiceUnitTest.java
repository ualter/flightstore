package ujr.flightstore.test.airplane.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ujr.flightstore.airplane.service.AirplaneService;

// https://dzone.com/articles/unit-and-integration-tests-in-spring-boot-2

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirplaneServiceUnitTest {
	
	@Autowired
	private AirplaneService airplaneService;
	
	
	@Test
	public void testList() {
		
		// Here should Mock the SOURCE of the Airplanes (avoid the integration with DB, API, etc.)
		// It is a unit test, not a integration test, so everything besides AirplaneService must be mocked
		
		assertThat(airplaneService.list())
			.isNotNull();
			//.allMatch(p -> p.getManufacturer().getName().toLowerCase().equals("airbus") );
	}

}
