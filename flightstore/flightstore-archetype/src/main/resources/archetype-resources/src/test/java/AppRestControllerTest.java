package ujr.flightstore.airplane.unit.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ujr.flightstore.airplane.controller.AirplaneRestController;
import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.service.AirplaneService;
import ujr.flightstore.airplane.service.ManufacturerService;

//@RunWith(SpringRunner.class)
//@WebMvcTest(${artifactId}RestController.class)
public class ${artifactId}RestControllerTest {
	
	/*
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ${artifactId}Service ${artifactId}Service;
	@MockBean
	private ManufacturerService manufacturerServiceService;
	
	private Manufacturer boeing;
	private Manufacturer airbus;
	private Airplane airplane;
	
	@Test
	public void whenList_thenReturnAllAirplanes() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/airplanes")
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(6)))
				    .andExpect(jsonPath("$[0].model", is("B737")))
					.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
	}

	@Test
	public void whenFindByManufacturerBoeing_thenReturnAllBoeingAirplanes() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/airplanes/manufacturer/" + this.boeing.getId())
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(4)))
					.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
	}
	
	@Test
	public void whenCreateAirplane_thenReturnAirplane() throws Exception {
		this.createServiceEnviroment();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonContent = objectMapper.writeValueAsString(this.airplane);
		
		MvcResult result = mvc.perform(post("/api/v1/airplanes")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonContent))
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.manufacturer.name", is("Boeing")))
					.andReturn();
		
		@SuppressWarnings("unused")
		String content = result.getResponse().getContentAsString();
	}
	
	private void createServiceEnviroment() {
		this.boeing = Manufacturer.builder().id(1L).name("Boeing").build();
		this.airbus = Manufacturer.builder().id(2L).name("Airbus").build();
		
		this.airplane = Airplane.builder()
				.id(33L)
				.model("B737")
				.rangeKm(15000)
				.seats(180)
				.manufacturer(this.boeing)
				.build();
		
		List<Airplane> listAirplanes = new ArrayList<Airplane>();
		listAirplanes.add(Airplane.builder().model("B737").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("B737").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("B737").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("B777").manufacturer(boeing).build());
		listAirplanes.add(Airplane.builder().model("A320").manufacturer(airbus).build());
		listAirplanes.add(Airplane.builder().model("A350").manufacturer(airbus).build());
		List<Airplane> listAirplanesBoeing = new ArrayList<Airplane>();
		listAirplanes.forEach(a -> {
			if ( a.getManufacturer().getId() == boeing.getId() ) listAirplanesBoeing.add(a);
		});
		
		
		BDDMockito.given(airplaneService.list()).willReturn(listAirplanes);
		BDDMockito.given(airplaneService.findByManufacturer(this.boeing.getId())).willReturn(listAirplanesBoeing);
		BDDMockito.given(airplaneService.save(any(Airplane.class))).willReturn(this.airplane);
	}
	
	*/
}
