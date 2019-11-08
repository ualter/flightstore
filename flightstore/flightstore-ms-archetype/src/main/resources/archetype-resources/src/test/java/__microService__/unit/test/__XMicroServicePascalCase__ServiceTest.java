package ${package}.${microService}.unit.test;

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

import ujr.flightstore.${microService}.model.${XMicroServicePascalCase};
import ujr.flightstore.${microService}.model.Manufacturer;
import ujr.flightstore.${microService}.service.${XMicroServicePascalCase}Service;
import ujr.flightstore.${microService}.service.ManufacturerService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ${XMicroServicePascalCase}ServiceTest {
	
	@TestConfiguration
	static class ${XMicroServicePascalCase}ServiceTestContextConfiguration {
		@Bean
		public ${XMicroServicePascalCase}Service ${microService}Service() {
			return new ${microService}Service();
		}
		
		@Bean
		public OtherService otherService() {
			return new OtherService();
		}
	}
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ${XMicroServicePascalCase}Service ${microService}Service;
	
	@Autowired
	private OtherService otherService;
	
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

			entityManager.persist(${XMicroServicePascalCase}.builder().model("B737").manufacturer(boeing).seats(150).build());
			entityManager.persist(${XMicroServicePascalCase}.builder().model("B737").manufacturer(boeing).seats(150).build());
			entityManager.persist(${XMicroServicePascalCase}.builder().model("B737").manufacturer(boeing).seats(150).build());
			entityManager.persist(${XMicroServicePascalCase}.builder().model("B777").manufacturer(boeing).seats(280).build());
			entityManager.persist(${XMicroServicePascalCase}.builder().model("A320").manufacturer(airbus).seats(120).build());
			entityManager.persist(${XMicroServicePascalCase}.builder().model("A350").manufacturer(airbus).seats(290).build());
			entityManager.persist(${XMicroServicePascalCase}.builder().model("A380").manufacturer(airbus).seats(500).build());
			entityManager.flush();
			this.setUp = true;
		}
	}
	
	@Test
	public void whenList_thenReturnAll${XMicroServicePascalCase}s() {
		assertThat(${microService}Service.list())
			.isNotNull()
			.hasSize(7)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty());
	}
	
	@Test
	public void whenListPageable_thenReturnFirstPageOf${XMicroServicePascalCase}s() {
		
		Pageable firstPage = PageRequest.of(0, this.pageSize);
		
		assertThat(${microService}Service.list(firstPage))
			.isNotNull()
			.hasSize(3)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty());
	}
	
	@Test
	public void whenListPageableSortAsc_thenReturnFirstPageOfSort${XMicroServicePascalCase}s() {
		// Ascending Order Seats (120)
		Page<Airplane> result = ${microService}Service.list(PageRequest.of(0, this.pageSize, Sort.by("seats")));
		assertThat(result)
			.isNotNull()
			.hasSize(3);
		assertThat(result.getContent().get(0).getSeats()).isEqualTo(120);
		assertThat(result.getContent().get(0).getModel()).isEqualToIgnoringCase("A320");
		
		// Descending Order Seats (500)
		result = ${microService}Service.list(PageRequest.of(0, this.pageSize, Sort.by("seats").descending()));
		assertThat(result)
			.isNotNull()
			.hasSize(3);
		assertThat(result.getContent().get(0).getSeats()).isEqualTo(500);
		assertThat(result.getContent().get(0).getModel()).isEqualToIgnoringCase("A380");
	}
	
	@Test
	public void whenFindByModelB737_thenReturnB737${XMicroServicePascalCase}s() {
		assertThat(${microService}Service.findByModel("B737"))
			.isNotNull()
			.hasSize(3)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty())
			.allMatch(p -> p.getManufacturer().getName().toLowerCase().equals("boeing") )
			.allMatch(a -> a.getModel().equals("B737") );
	}
	
	@Test
	public void whenFindByManufactorerBoeing_thenReturnBoeing{XMicroServicePascalCase}s() {
		assertThat(${microService}Service.findByManufacturer(boeing.getId()))
			.isNotNull()
			.hasSize(4)
			.allMatch(p -> !p.getManufacturer().getName().isEmpty())
			.allMatch(p -> p.getManufacturer().getName().toLowerCase().equals("boeing") );
	}

}
