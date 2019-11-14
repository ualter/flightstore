package ujr.flightstore.airliner.unit.test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import ujr.flightstore.airliner.controller.AirlinerRestController;
import ujr.flightstore.airliner.model.Airliner;
import ujr.flightstore.airliner.service.AirlinerService;

@RunWith(SpringRunner.class)
@WebMvcTest(AirlinerRestController.class)
public class AirlinerRestControllerTest {
	/*
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AirlinerService airlinerService;
	
	private Airliner airliner;
	
	@Test
	public void whenList_thenReturnAllAirliners() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/airliners")
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(6)))
				    .andExpect(jsonPath("$[0].name", is("Iberia")))
					.andExpect(jsonPath("$[0].airplanes", hasSize(3)));
	}

	@Test
	public void whenFindByNameIberia_thenReturnIberiaAirliner() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/airliners/name/iberia")
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].name", is("Iberia")));
	}
	
	@Test
	public void whenCreateAirliner_thenReturnAirliner() throws Exception {
		this.createServiceEnviroment();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonContent = objectMapper.writeValueAsString(this.airliner);
		
		MvcResult result = mvc.perform(post("/api/v1/airliners")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonContent))
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.name", is("Iberia")))
					.andReturn();
		
		@SuppressWarnings("unused")
		String content = result.getResponse().getContentAsString();
	}
	
	private void createServiceEnviroment() {
		Set<Long> airplanesId = new HashSet<Long>();
		airplanesId.add(1L);
		airplanesId.add(2L);
		airplanesId.add(3L);
		
		this.airliner = Airliner.builder()
				.id(33L)
				.name("Iberia")
				.airplaneIds(airplanesId)
				.build();
		
		List<Airliner> listAirliners = new ArrayList<Airliner>();
		listAirliners.add(Airliner.builder().name("Iberia").airplaneIds(airplanesId).build());
		listAirliners.add(Airliner.builder().name("Air British").airplaneIds(airplanesId).build());
		listAirliners.add(Airliner.builder().name("Air France").airplaneIds(airplanesId).build());
		listAirliners.add(Airliner.builder().name("TAP").airplaneIds(airplanesId).build());
		listAirliners.add(Airliner.builder().name("Air Italia").airplaneIds(airplanesId).build());
		listAirliners.add(Airliner.builder().name("KLM").airplaneIds(airplanesId).build());
		List<Airliner> listAirlinersIberica = new ArrayList<Airliner>();
		listAirliners.forEach(a -> {
			if ( a.getName() == "Iberia" ) listAirlinersIberica.add(a);
			if ( a.getName() == "TAP" ) listAirlinersIberica.add(a);
		});
		
		List<Airliner> listIberia = new ArrayList<Airliner>();
		listIberia.add(this.airliner);
		
		BDDMockito.given(airlinerService.list()).willReturn(listAirliners);
		BDDMockito.given(airlinerService.findByName(any(String.class))).willReturn(listIberia);
		BDDMockito.given(airlinerService.save(any(Airliner.class))).willReturn(this.airliner);
	}
	*/
}
