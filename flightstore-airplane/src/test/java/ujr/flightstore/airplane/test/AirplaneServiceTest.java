package ujr.flightstore.airplane.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.service.AirplaneService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AirplaneServiceTest {
	
	@TestConfiguration
	static class AirplaneServiceTestContextConfiguration {
		@Bean
		public AirplaneService airplaneService() {
			return new AirplaneService();
		}
	}
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private AirplaneService airplaneService;
	
	private boolean setUp = false;
	
	@Before
	public void setMockOutput() {
		if (!this.setUp) {
			Manufacturer boeing = Manufacturer.builder().name("Boeing").build();
			Manufacturer airbus = Manufacturer.builder().name("Airbus").build();
			entityManager.persist(boeing);
			entityManager.persist(airbus);
			entityManager.flush();

			entityManager.persist(Airplane.builder().model("B737").manufacturer(boeing).build());
			entityManager.persist(Airplane.builder().model("B737").manufacturer(boeing).build());
			entityManager.persist(Airplane.builder().model("B737").manufacturer(boeing).build());
			entityManager.persist(Airplane.builder().model("B777").manufacturer(boeing).build());
			entityManager.persist(Airplane.builder().model("A320").manufacturer(airbus).build());
			entityManager.persist(Airplane.builder().model("A350").manufacturer(airbus).build());
			entityManager.flush();
			this.setUp = true;
		}
	}
	
	@Test
	public void whenList_thenReturnAllAirplanes() {
		assertThat(airplaneService.list())
			.isNotNull()
			.hasSize(6)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty());
	}
	
	@Test
	public void whenFindByModelBoeing_thenReturnBoeingAirplanes() {
		assertThat(airplaneService.findByModel("B737"))
			.isNotNull()
			.hasSize(3)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty())
			.allMatch(p -> p.getManufacturer().getName().toLowerCase().equals("boeing") )
			.allMatch(a -> a.getModel().equals("B737") );
	}
	

}
