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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ujr.flightstore.Messages;
import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.service.AirplaneService;
import ujr.flightstore.exception.ResourceNotFoundException;


// Check: https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg

@Api(value="Airplane REST API")
@RestController
@RequestMapping(path = "/api/v1")
public class AirplaneRestController {
	
	@Autowired
	private AirplaneService airplaneService;

	@ApiOperation(value = "List of available airplanes", response = List.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(path = "/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Airplane> list() {
		return this.airplaneService.list();
	}

	@ApiOperation(value = "List of airplanes of a specific Manufacturer", response = List.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(path = "/airplanes/manufacturer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Airplane> findByManufacturer(@PathVariable(value = "id") Long manufacturerId) {
		return this.airplaneService.findByManufacturer(manufacturerId);
	}

	@ApiOperation(value = "Find a specific Airplane", response = Airplane.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
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
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Successfully created the airplane"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(path = "/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Airplane create(@RequestBody Airplane airplane) {
		return this.airplaneService.save(airplane);
	}
	
	@ApiOperation(value = "Update an airplane", response = Void.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 202, message = "Successfully updated the airplane"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PutMapping(path = "/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void update(@RequestBody Airplane airplane) {
		this.airplaneService.save(airplane);
	}
	
	

}
