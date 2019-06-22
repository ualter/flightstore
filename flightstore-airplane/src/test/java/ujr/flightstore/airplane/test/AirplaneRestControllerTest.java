package ujr.flightstore.airplane.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ujr.flightstore.airplane.api.AirplaneRestController;
import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.service.AirplaneService;


@RunWith(SpringRunner.class)
@WebMvcTest(AirplaneRestController.class)
public class AirplaneRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AirplaneService airplaneService;
	
	@Test
	public void whenList_thenReturnAllAirplanes() throws Exception {
		
		Manufacturer boeing = Manufacturer.builder().name("Boeing").build();
		Manufacturer airbus = Manufacturer.builder().name("Airbus").build();
		List<Airplane> listAirplanes = new ArrayList<Airplane>();
		listAirplanes.add(Airplane.builder().model("B737").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("B737").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("B737").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("B777").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("A320").manufacturer(airbus).build());
		listAirplanes.add(Airplane.builder().model("A350").manufacturer(airbus).build());
		BDDMockito.given(airplaneService.list()).willReturn(listAirplanes);
		
		mvc.perform(get("/api/v1/airplanes")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(6)))
			    .andExpect(jsonPath("$[0].model", is("B737")))
				.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
				
	}                
}
