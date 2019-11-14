package ujr.flightstore.airliner.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ujr.flightstore.airliner.model.Airliner;
import ujr.flightstore.airliner.service.AirlinerService;
import ujr.flightstore.airplane.client.AirplaneClient;
import ujr.flightstore.airplane.client.view.AirplaneView;
import ujr.flightstore.controller.CommonApiResponsesCreation;
import ujr.flightstore.controller.CommonApiResponsesQuery;
import ujr.flightstore.controller.CommonApiResponsesUpdate;
import ujr.flightstore.exception.ResourceNotFoundException;

@Slf4j
@Api(value = "Flightstore", description = "REST API for Airliner", tags = { "Airliner" })
@RestController
@RequestMapping(path = "/api/v1")
public class AirlinerRestController {
	
	@Autowired
	private AirlinerService airlinerService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AirplaneClient airplaneClient;

	@ApiOperation(value = "List of available airliners", response = List.class, tags = { "Airliner" } )
	@CommonApiResponsesQuery
	@GetMapping(path = "/airliners", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Airliner> list() {
		return this.airlinerService.list();
	}

	@ApiOperation(value = "Find a specific Airliner", response = Airliner.class)
	@CommonApiResponsesQuery
	@GetMapping(path = "/airliners/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Airliner findById(@PathVariable(value = "id") Long id) {
		Airliner airliner = this.airlinerService.findById(id);
		if ( airliner == null ) {
			throw new ResourceNotFoundException(
				messageSource.getMessage("errors.resource_not_found", new Object[]{"Airliner",id}, Locale.getDefault())
			);
		}
		
		// Consulting the Airplanes Data from Airplane Micro Services
		// Preparing the List of Ids (Long)
		List<Long> listAirplaneIds = new ArrayList<Long>();
		airliner.getAirplanes().forEach(a -> { 
			listAirplaneIds.add(a.getId()); 
		});
		// Requesting all of the Airplanes and fill up the Airline collection with them
		List<AirplaneView> airplanes = this.airplaneClient.findByIds(listAirplaneIds);
		if (airplanes == null || airplanes.size() == 0 ) {
			log.warn("Nothing found for the requested airplanes Ids: " + listAirplaneIds);
		} else {
			airliner.getAirplanes().forEach(airplane -> {
				AirplaneView airplaneView = airplanes.stream()
							.filter(a -> a.getId() == airplane.getId())
							.findFirst()
							.orElse(null);
				if ( airplaneView != null ) {
					airplane.setModel(airplaneView.getModel());
					airplane.setRangeKm(airplaneView.getRangeKm());
					airplane.setSeats(airplaneView.getSeats());
					airplane.setManufacturerView(airplaneView.getManufacturerView());
				} else {
					log.warn("Airplane Id \"{}\" not found!", airplane.getId());
					airplane.setModel("Not Found Id=" + airplane.getId());
				}
			});
		}

		return airliner;
	}
	
	@ApiOperation(value = "Find Airliners by its Name", response = List.class, tags = { "Airliner" } )
	@CommonApiResponsesQuery
	@GetMapping(path = "/airliners/name/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Airliner> findById(@PathVariable(value = "name") String name) {
		List<Airliner> listAirliner = this.airlinerService.findByName(name);
		if ( listAirliner == null || listAirliner.size() == 0 ) {
			throw new ResourceNotFoundException(
				messageSource.getMessage("errors.resource_not_found_byName", new Object[]{"Airliners",name}, Locale.getDefault())
			);
		}
		return listAirliner;
	}
	
	@ApiOperation(value = "Delete a specific Airliner", response = Airliner.class)
	@CommonApiResponsesQuery
	@DeleteMapping(path = "/airliners/{id}")
	public void removeById(@PathVariable(value = "id") Long id) {
		Airliner airliner = this.airlinerService.findById(id);
		if ( airliner == null ) {
			throw new ResourceNotFoundException(
				messageSource.getMessage("errors.resource_not_found", new Object[]{"Airliner",id}, Locale.getDefault())
			);
		}
		this.airlinerService.remove(airliner);
	}
	
	@ApiOperation(value = "Create an airliner", response = Airliner.class)
	@CommonApiResponsesCreation
	@PostMapping(path = "/airliners", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Airliner create(@RequestBody Airliner airliner) {
		return this.airlinerService.save(airliner);
	}
	
	@ApiOperation(value = "Update an airliner", response = Void.class)
	@CommonApiResponsesUpdate
	@PutMapping(path = "/airliners", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void update(@RequestBody Airliner airliner) {
		this.airlinerService.save(airliner);
	}
	
	@ApiOperation(value = "Clean airliners cache", response = List.class, tags = { "Airliner" } )
	@CommonApiResponsesQuery
	@GetMapping(path = "/airliners/cache/clean", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void clean() {
		this.airlinerService.cleanCache();
	}
	
	

}
