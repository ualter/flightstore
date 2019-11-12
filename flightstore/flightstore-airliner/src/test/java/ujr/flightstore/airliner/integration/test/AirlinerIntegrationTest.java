package ujr.flightstore.airliner.integration.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ujr.flightstore.airliner.AirlinerApp;
import ujr.flightstore.airliner.model.Airliner;
import ujr.flightstore.airliner.service.AirlinerService;


/**
 * At Integration Test, everything of Spring layers are loaded, all the application context. 
 * So, this could be very heavy and slow, most of the time it is better (or enough) to choose an Unit Test (with some layers Mocked and controlled by you, 
 * according to the desired scenario to be tested). Use Integration Test only when it is really necessary to test all of the layers controlled and 
 * loaded by the Spring framework, none by you!  Check accordingly each case.  
 * @author Ualter Jr.
 */
@RunWith(SpringRunner.class)
@IfProfileValue(name="integration-tests", value="true")
@SpringBootTest(classes = { AirlinerApp.class }, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AirlinerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AirlinerService airlinerService;
	
	@Test
	public void whenList_thenReturnAllAirliners() throws Exception {
		Set<Long> airplanesId = new HashSet<Long>();
		airplanesId.add(1L);
		airplanesId.add(2L);
		airplanesId.add(3L);
		
		airlinerService.save(Airliner.builder().name("Iberia").airplanes(airplanesId).build());
		airlinerService.save(Airliner.builder().name("Air British").airplanes(airplanesId).build());
		airlinerService.save(Airliner.builder().name("Air France").airplanes(airplanesId).build());
		airlinerService.save(Airliner.builder().name("TAP").airplanes(airplanesId).build());
		airlinerService.save(Airliner.builder().name("Air Italia").airplanes(airplanesId).build());
		airlinerService.save(Airliner.builder().name("KLM").airplanes(airplanesId).build());
		
		mvc.perform(get("/api/v1/airliners")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(6)))
			    .andExpect(jsonPath("$[0].name", is("Iberia")));
				
	}

}
