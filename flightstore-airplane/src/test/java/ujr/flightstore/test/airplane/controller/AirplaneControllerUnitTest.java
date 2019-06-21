package ujr.flightstore.test.airplane.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ujr.flightstore.airplane.api.AirplaneController;
import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.service.AirplaneService;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirplaneControllerUnitTest {

	MockMvc mockMvc;

	@Autowired
	private AirplaneController airplaneController;

	@MockBean
	private AirplaneService airplaneService;

	private List<Airplane> airplanes;

	@Before
	public void setup() throws Exception {

		this.mockMvc = MockMvcBuilders.standaloneSetup(this.airplaneController).build();

		Manufacturer boeing = Manufacturer.builder().id(1).name("Boeing").build();
		Manufacturer airbus = Manufacturer.builder().id(2).name("Airbus").build();

		this.airplanes = new ArrayList<Airplane>();
		this.airplanes.add(Airplane.builder().id(1).model("B737").manufacturer(boeing).build());
		this.airplanes.add(Airplane.builder().id(2).model("B777").manufacturer(boeing).build());
		this.airplanes.add(Airplane.builder().id(3).model("A320").manufacturer(airbus).build());
		this.airplanes.add(Airplane.builder().id(4).model("A350").manufacturer(airbus).build());

	}

	@Test
	public void testList() throws Exception {
		when(airplaneService.list()).thenReturn(this.airplanes);

		this.mockMvc
			.perform(get("/flightstore-airplane/api/v1/airplanes").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$[0].title", is("Hokuto no ken")));
	}

}
