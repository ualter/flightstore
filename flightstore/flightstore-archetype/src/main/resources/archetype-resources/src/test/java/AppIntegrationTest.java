package ujr.flightstore.${artifactId}.integration.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import ujr.flightstore.airplane.AirplaneApp;
import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.repository.ManufacturerRepository;
import ujr.flightstore.airplane.service.AirplaneService;


/**
 * At Integration Test, everything of Spring layers are loaded, all the application context. 
 * So, this could be very heavy and slow, most of the time it is better (or enough) to choose an Unit Test (with some layers Mocked and controlled by you, according to the desired scenario to be tested).
 * Use Integration Test only when it is really necessary to test all of the layers controlled and loaded by the Spring framework, none by you!  Check accordingly each case.  
 * @author Ualter Jr.
 */
//@RunWith(SpringRunner.class)
//@IfProfileValue(name="integration-tests", value="true")
//@SpringBootTest(classes = { AirplaneApp.class }, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ${artifactId}IntegrationTest {

	/*
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AirplaneService airplaneService;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Test
	public void whenList_thenReturnAllAirplanes() throws Exception {
		
		Manufacturer boeing = Manufacturer.builder().name("Boeing").build();
		Manufacturer airbus = Manufacturer.builder().name("Airbus").build();
		manufacturerRepository.save(boeing);
		manufacturerRepository.save(airbus);
		
		airplaneService.save(Airplane.builder().model("B737").manufacturer(boeing).build());
		airplaneService.save(Airplane.builder().model("B737").manufacturer(boeing).build());
		airplaneService.save(Airplane.builder().model("B737").manufacturer(boeing).build());
		airplaneService.save(Airplane.builder().model("B777").manufacturer(boeing).build());
		airplaneService.save(Airplane.builder().model("A320").manufacturer(airbus).build());
		airplaneService.save(Airplane.builder().model("A350").manufacturer(airbus).build());
		
		mvc.perform(get("/api/v1/airplanes")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(6)))
			    .andExpect(jsonPath("$[0].model", is("B737")))
				.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
				
	} 
	
	*/

}
