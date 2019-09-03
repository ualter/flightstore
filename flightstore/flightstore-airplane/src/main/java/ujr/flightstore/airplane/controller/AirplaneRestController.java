package ujr.flightstore.airplane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import ujr.flightstore.Messages;
import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.service.AirplaneService;
import ujr.flightstore.controller.CommonApiResponsesCreation;
import ujr.flightstore.controller.CommonApiResponsesQuery;
import ujr.flightstore.controller.CommonApiResponsesUpdate;
import ujr.flightstore.exception.ResourceNotFoundException;


// Check: https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg

@Api(value = "Flightstore", description = "REST API for Airplane", tags = { "Airplane" })
@RestController
@RequestMapping(path = "/api/v1")
public class AirplaneRestController {
	
	@Autowired
	private AirplaneService airplaneService;

	@ApiOperation(value = "List of available airplanes", response = List.class, tags = { "Airplane" } )
	@CommonApiResponsesQuery
	@GetMapping(path = "/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Airplane> list() {
		return this.airplaneService.list();
	}

	@ApiOperation(value = "List of airplanes of a specific Manufacturer", response = List.class)
	@CommonApiResponsesQuery
	@GetMapping(path = "/airplanes/manufacturer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Airplane> findByManufacturer(@PathVariable(value = "id") Long manufacturerId) {
		return this.airplaneService.findByManufacturer(manufacturerId);
	}

	@ApiOperation(value = "Find a specific Airplane", response = Airplane.class)
	@CommonApiResponsesQuery
	@GetMapping(path = "/airplanes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Airplane findById(@PathVariable(value = "id") Long id) {
		Airplane airplane = this.airplaneService.findById(id);
		if ( airplane == null ) {
			throw new ResourceNotFoundException(Messages.message404("Airplane", id));
		}
		return airplane;
	}
	
	@ApiOperation(value = "Create an airplane", response = Airplane.class)
	@CommonApiResponsesCreation
	@PostMapping(path = "/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Airplane create(@RequestBody Airplane airplane) {
		return this.airplaneService.save(airplane);
	}
	
	@ApiOperation(value = "Update an airplane", response = Void.class)
	@CommonApiResponsesUpdate
	@PutMapping(path = "/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void update(@RequestBody Airplane airplane) {
		this.airplaneService.save(airplane);
	}
	
	

}
