package ujr.flightstore.airliner.unit.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import ujr.flightstore.airliner.controller.AirlinerRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(AirlinerRestController.class)
public class AirlinerRestControllerTest {
	
	/*
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AirlinerService airlinerService;
	@MockBean
	private ManufacturerService manufacturerServiceService;
	
	private Manufacturer boeing;
	private Manufacturer airbus;
	private Airliner airliner;
	
	@Test
	public void whenList_thenReturnAll${microServices}() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/airliners")
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(6)))
				    .andExpect(jsonPath("$[0].model", is("B737")))
					.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
	}

	@Test
	public void whenFindByManufacturerBoeing_thenReturnAllBoeingAirliner() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/airliners/manufacturer/" + this.boeing.getId())
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(4)))
					.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
	}
	
	@Test
	public void whenCreateAirplane_thenReturnairliner() throws Exception {
		this.createServiceEnviroment();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonContent = objectMapper.writeValueAsString(this.airliner);
		
		MvcResult result = mvc.perform(post("/api/v1/airliners")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonContent))
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(jsonPath(".manufacturer", is("Boeing")))
					.andReturn();
		
		@SuppressWarnings("unused")
		String content = result.getResponse().getContentAsString();
	}
	
	private void createServiceEnviroment() {
		this.boeing = Manufacturer.builder().id(1L).name("Boeing").build();
		this.airbus = Manufacturer.builder().id(2L).name("Airbus").build();
		
		this.airliner = Airliner.builder()
				.id(33L)
				.model("B737")
				.rangeKm(15000)
				.seats(180)
				.manufacturer(this.boeing)
				.build();
		
		List<Airliner> listAirliners = new ArrayList<Airliner>();
		listAirliners.add(Airliner.builder().model("B737").manufacturer(boeing).build());
		listAirliners.add(Airliner.builder().model("B737").manufacturer(boeing).build());
		listAirliners.add(Airliner.builder().model("B737").manufacturer(boeing).build());
		listAirliners.add(Airliner.builder().model("B777").manufacturer(boeing).build());
		listAirliners.add(Airliner.builder().model("A320").manufacturer(airbus).build());
		listAirliners.add(Airliner.builder().model("A350").manufacturer(airbus).build());
		List<Airliner> listAirlinersBoeing = new ArrayList<Airliner>();
		listAirliners.forEach(a -> {
			if ( a.getManufacturer().getId() == boeing.getId() ) listAirlinersBoeing.add(a);
		});
		
		
		BDDMockito.given(airlinerService.list()).willReturn(listAirlineres);
		BDDMockito.given(airlinerService.findByManufacturer(this.boeing.getId())).willReturn(listAirlinersBoeing);
		BDDMockito.given(airlinerService.save(any(Airliner.class))).willReturn(this.airliner);
	}
	*/
	
}
