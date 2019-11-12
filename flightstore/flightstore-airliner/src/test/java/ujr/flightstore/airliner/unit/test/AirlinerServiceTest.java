package ujr.flightstore.airliner.unit.test;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.test.context.junit4.SpringRunner;

import ujr.flightstore.airliner.model.Airliner;
import ujr.flightstore.airliner.service.AirlinerService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AirlinerServiceTest {
	
	@TestConfiguration
	static class AirlinerServiceTestContextConfiguration {
		@Bean
		public AirlinerService airlinerService() {
			return new AirlinerService();
		}
		
	}
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private AirlinerService airlinerService;
	
	private boolean setUp = false;
	private int pageSize = 3;
	
	
	@Before
	public void setMockOutput() {
		if (!this.setUp) {
			Set<Long> airplanesId = new HashSet<Long>();
			airplanesId.add(1L);
			airplanesId.add(2L);
			airplanesId.add(3L);
			entityManager.persist(Airliner.builder().name("Iberia").airplanes(airplanesId).build());
			entityManager.persist(Airliner.builder().name("Air British").airplanes(airplanesId).build());
			entityManager.persist(Airliner.builder().name("Air France").airplanes(airplanesId).build());
			entityManager.persist(Airliner.builder().name("TAP").airplanes(airplanesId).build());
			entityManager.persist(Airliner.builder().name("Air Italia").airplanes(airplanesId).build());
			entityManager.persist(Airliner.builder().name("KLM").airplanes(airplanesId).build());
			entityManager.flush();
			this.setUp = true;
		}
	}
	
	@Test
	public void whenList_thenReturnAllAirliners() {
		assertThat(airlinerService.list())
			.isNotNull()
			.hasSize(6)
			.allMatch(p -> !p.getName().isEmpty());
	}
	
	@Test
	public void whenListPageable_thenReturnFirstPageOfAirliners() {
		
		Pageable firstPage = PageRequest.of(0, this.pageSize);
		
		assertThat(airlinerService.list(firstPage))
			.isNotNull()
			.hasSize(3)
			.allMatch(p -> !p.getName().isEmpty());
	}
	
	@Test
	public void whenListPageableSortAsc_thenReturnFirstPageOfSortAirliners() {
		// Ascending Order Name (Air British)
		Page<Airliner> result = airlinerService.list(PageRequest.of(0, this.pageSize, Sort.by("name")));
		assertThat(result)
			.isNotNull()
			.hasSize(3);
		assertThat(result.getContent().get(0).getName()).isEqualTo("Air British");
		
		// Descending Order Name (KLM)
		result = airlinerService.list(PageRequest.of(0, this.pageSize, Sort.by("name").descending()));
		assertThat(result)
			.isNotNull()
			.hasSize(3);
		assertThat(result.getContent().get(0).getName()).isEqualTo("TAP");
	}
	
	@Test
	public void whenFindByNameIbeira_thenReturnIberiaAirliners() {
		assertThat(
				airlinerService.findByName("Iberia"))
					.isNotNull()
					.hasSize(1)
					.allMatch(p -> p.getName().toLowerCase().equals("iberia"));
	}

}
