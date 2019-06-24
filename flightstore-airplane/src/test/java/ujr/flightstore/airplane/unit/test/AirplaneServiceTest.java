package ujr.flightstore.airplane.unit.test;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.service.AirplaneService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource("/application-test.properties")
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
	private int pageSize = 3;
	private Manufacturer boeing;
	private Manufacturer airbus;
	
	@Before
	public void setMockOutput() {
		if (!this.setUp) {
			this.boeing = Manufacturer.builder().name("Boeing").build();
			this.airbus = Manufacturer.builder().name("Airbus").build();
			this.boeing = entityManager.persist(boeing);
			this.airbus = entityManager.persist(airbus);
			entityManager.flush();

			entityManager.persist(Airplane.builder().model("B737").manufacturer(boeing).seats(150).build());
			entityManager.persist(Airplane.builder().model("B737").manufacturer(boeing).seats(150).build());
			entityManager.persist(Airplane.builder().model("B737").manufacturer(boeing).seats(150).build());
			entityManager.persist(Airplane.builder().model("B777").manufacturer(boeing).seats(280).build());
			entityManager.persist(Airplane.builder().model("A320").manufacturer(airbus).seats(120).build());
			entityManager.persist(Airplane.builder().model("A350").manufacturer(airbus).seats(290).build());
			entityManager.persist(Airplane.builder().model("A380").manufacturer(airbus).seats(500).build());
			entityManager.flush();
			this.setUp = true;
		}
	}
	
	@Test
	public void whenList_thenReturnAllAirplanes() {
		assertThat(airplaneService.list())
			.isNotNull()
			.hasSize(7)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty());
	}
	
	@Test
	public void whenListPageable_thenReturnFirstPageOfAirplanes() {
		
		Pageable firstPage = PageRequest.of(0, this.pageSize);
		
		assertThat(airplaneService.list(firstPage))
			.isNotNull()
			.hasSize(3)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty());
	}
	
	@Test
	public void whenListPageableSortAsc_thenReturnFirstPageOfSortAirplanes() {
		// Ascending Order Seats (120)
		Page<Airplane> result = airplaneService.list(PageRequest.of(0, this.pageSize, Sort.by("seats")));
		assertThat(result)
			.isNotNull()
			.hasSize(3);
		assertThat(result.getContent().get(0).getSeats()).isEqualTo(120);
		assertThat(result.getContent().get(0).getModel()).isEqualToIgnoringCase("A320");
		
		// Descending Order Seats (500)
		result = airplaneService.list(PageRequest.of(0, this.pageSize, Sort.by("seats").descending()));
		assertThat(result)
			.isNotNull()
			.hasSize(3);
		assertThat(result.getContent().get(0).getSeats()).isEqualTo(500);
		assertThat(result.getContent().get(0).getModel()).isEqualToIgnoringCase("A380");
	}
	
	@Test
	public void whenFindByModelB737_thenReturnB737Airplanes() {
		assertThat(airplaneService.findByModel("B737"))
			.isNotNull()
			.hasSize(3)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty())
			.allMatch(p -> p.getManufacturer().getName().toLowerCase().equals("boeing") )
			.allMatch(a -> a.getModel().equals("B737") );
	}
	
	@Test
	public void whenFindByManufactorerBoeing_thenReturnBoeingAirplanes() {
		assertThat(airplaneService.findByManufacturer(boeing.getId()))
			.isNotNull()
			.hasSize(4)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty())
			.allMatch(p -> p.getManufacturer().getName().toLowerCase().equals("boeing") );
	}
	

}
