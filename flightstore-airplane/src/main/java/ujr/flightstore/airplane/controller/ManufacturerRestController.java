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
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.service.ManufacturerService;


@Api(value="Manufacturer REST API")
@RestController
@RequestMapping(path = "/api/v1")
public class ManufacturerRestController {
	
	@Autowired
	private ManufacturerService manufacturerService;

	@ApiOperation(value = "List of available manufacturers", response = List.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(path = "/manufacturers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Manufacturer> list() {
		return this.manufacturerService.list();
	}

	@ApiOperation(value = "Find a specific Manufacturer", response = Manufacturer.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(path = "/manufacturers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Manufacturer findById(@PathVariable(value = "id") Long id) {
		return this.manufacturerService.findById(id);
	}
	
	@ApiOperation(value = "Create an manufacturer", response = Manufacturer.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Successfully created the manufacturer"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(path = "/manufacturers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Manufacturer create(@RequestBody Manufacturer manufacturer) {
		return this.manufacturerService.save(manufacturer);
	}
	
	@ApiOperation(value = "Update an manufacturer", response = Void.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 202, message = "Successfully updated the manufacturer"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PutMapping(path = "/manufacturers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void update(@RequestBody Manufacturer manufacturer) {
		this.manufacturerService.save(manufacturer);
	}
	
	

}
