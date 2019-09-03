package ujr.flightstore.airplane.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.service.ManufacturerService;
import ujr.flightstore.controller.CommonApiResponsesCreation;
import ujr.flightstore.controller.CommonApiResponsesQuery;
import ujr.flightstore.controller.CommonApiResponsesUpdate;
import ujr.flightstore.exception.ResourceNotFoundException;


@Api(value = "Flightstore", description = "REST API for Manufacturers", tags = { "Manufacturer" })
@RestController
@RequestMapping(path = "/api/v1")
public class ManufacturerRestController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private MessageSource messageSource;
	
	@ApiOperation(value = "List of available manufacturers", response = List.class, tags = { "Manufacturer" })
	@CommonApiResponsesQuery
	@GetMapping(path = "/manufacturers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Manufacturer> list() {
		return this.manufacturerService.list();
	}

	@ApiOperation(value = "Find a specific Manufacturer", response = Manufacturer.class)
	@CommonApiResponsesQuery
	@GetMapping(path = "/manufacturers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Manufacturer findById(@PathVariable(value = "id") Long id) {
		Manufacturer manufacturer = this.manufacturerService.findById(id);
		if ( manufacturer == null ) {
			throw new ResourceNotFoundException(
				messageSource.getMessage("errors.resource_not_found", new Object[]{"Manufacturer",id}, Locale.getDefault())
			);
		}
		return manufacturer;
	}
	
	@ApiOperation(value = "Create an manufacturer", response = Manufacturer.class)
	@CommonApiResponsesCreation
	@PostMapping(path = "/manufacturers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Manufacturer create(@RequestBody Manufacturer manufacturer) {
		return this.manufacturerService.save(manufacturer);
	}
	
	@ApiOperation(value = "Update an manufacturer", response = Void.class)
	@CommonApiResponsesUpdate
	@PutMapping(path = "/manufacturers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void update(@RequestBody Manufacturer manufacturer) {
		this.manufacturerService.save(manufacturer);
	}
	
	

}
