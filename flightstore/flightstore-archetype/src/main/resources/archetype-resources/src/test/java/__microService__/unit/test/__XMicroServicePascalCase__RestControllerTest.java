package ${package}.${microService}.unit.test;

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

import ujr.flightstore.${microService}.controller.${XMicroServicePascalCase}RestController;
import ujr.flightstore.${microService}.model.${XMicroServicePascalCase};
import ujr.flightstore.${microService}.model.Manufacturer;
import ujr.flightstore.${microService}.service.${XMicroServicePascalCase}Service;
import ujr.flightstore.${microService}.service.ManufacturerService;

@RunWith(SpringRunner.class)
@WebMvcTest(${XMicroServicePascalCase}RestController.class)
public class ${XMicroServicePascalCase}RestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ${XMicroServicePascalCase}Service ${microService}Service;
	@MockBean
	private ManufacturerService manufacturerServiceService;
	
	private Manufacturer boeing;
	private Manufacturer airbus;
	private ${XMicroServicePascalCase} ${microService};
	
	@Test
	public void whenList_thenReturnAll${microServices}() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/${microService}s")
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(6)))
				    .andExpect(jsonPath("$[0].model", is("B737")))
					.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
	}

	@Test
	public void whenFindByManufacturerBoeing_thenReturnAllBoeing${XMicroServicePascalCase}() throws Exception {
		this.createServiceEnviroment();
		mvc.perform(get("/api/v1/${microService}s/manufacturer/" + this.boeing.getId())
				.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(4)))
					.andExpect(jsonPath("$[0].manufacturer.name", is("Boeing")));
	}
	
	@Test
	public void whenCreateAirplane_thenReturn${microService}() throws Exception {
		this.createServiceEnviroment();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonContent = objectMapper.writeValueAsString(this.${microService});
		
		MvcResult result = mvc.perform(post("/api/v1/${microService}s")
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
		
		this.${microService} = ${XMicroServicePascalCase}.builder()
				.id(33L)
				.model("B737")
				.rangeKm(15000)
				.seats(180)
				.manufacturer(this.boeing)
				.build();
		
		List<${XMicroServicePascalCase}> list${XMicroServicePascalCase}s = new ArrayList<${XMicroServicePascalCase}>();
		list${XMicroServicePascalCase}s.add(${XMicroServicePascalCase}.builder().model("B737").manufacturer(boeing).build());
		list${XMicroServicePascalCase}s.add(${XMicroServicePascalCase}.builder().model("B737").manufacturer(boeing).build());
		list${XMicroServicePascalCase}s.add(${XMicroServicePascalCase}.builder().model("B737").manufacturer(boeing).build());
		list${XMicroServicePascalCase}s.add(${XMicroServicePascalCase}.builder().model("B777").manufacturer(boeing).build());
		list${XMicroServicePascalCase}s.add(${XMicroServicePascalCase}.builder().model("A320").manufacturer(airbus).build());
		list${XMicroServicePascalCase}s.add(${XMicroServicePascalCase}.builder().model("A350").manufacturer(airbus).build());
		List<${XMicroServicePascalCase}> list${XMicroServicePascalCase}sBoeing = new ArrayList<${XMicroServicePascalCase}>();
		list${XMicroServicePascalCase}s.forEach(a -> {
			if ( a.getManufacturer().getId() == boeing.getId() ) list${XMicroServicePascalCase}sBoeing.add(a);
		});
		
		
		BDDMockito.given(${microService}Service.list()).willReturn(list${XMicroServicePascalCase}es);
		BDDMockito.given(${microService}Service.findByManufacturer(this.boeing.getId())).willReturn(list${XMicroServicePascalCase}sBoeing);
		BDDMockito.given(${microService}Service.save(any(${XMicroServicePascalCase}.class))).willReturn(this.${microService});
	}
	
}
