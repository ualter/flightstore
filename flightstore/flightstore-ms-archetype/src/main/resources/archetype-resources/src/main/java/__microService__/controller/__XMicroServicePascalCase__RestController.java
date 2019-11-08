package ${package}.${microService}.controller;

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
import ujr.flightstore.${microService}.model.${XMicroServicePascalCase};
import ujr.flightstore.${microService}.service.${XMicroServicePascalCase}Service;
import ujr.flightstore.controller.CommonApiResponsesCreation;
import ujr.flightstore.controller.CommonApiResponsesQuery;
import ujr.flightstore.controller.CommonApiResponsesUpdate;
import ujr.flightstore.exception.ResourceNotFoundException;


// Check: https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg

@Api(value = "Flightstore", description = "REST API for ${XMicroServicePascalCase}", tags = { "${XMicroServicePascalCase}" })
@RestController
@RequestMapping(path = "/api/v1")
public class ${XMicroServicePascalCase}RestController {
	
	@Autowired
	private ${XMicroServicePascalCase}Service ${microService}Service;
	
	@Autowired
	private MessageSource messageSource;

	@ApiOperation(value = "List of available ${microService}s", response = List.class, tags = { "${XMicroServicePascalCase}" } )
	@CommonApiResponsesQuery
	@GetMapping(path = "/${microService}s", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<${XMicroServicePascalCase}> list() {
		return this.${microService}Service.list();
	}

	@ApiOperation(value = "Find a specific ${XMicroServicePascalCase}", response = ${XMicroServicePascalCase}.class)
	@CommonApiResponsesQuery
	@GetMapping(path = "/${microService}s/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ${XMicroServicePascalCase} findById(@PathVariable(value = "id") Long id) {
		${XMicroServicePascalCase} ${microService} = this.${microService}Service.findById(id);
		if ( ${microService} == null ) {
			throw new ResourceNotFoundException(
				messageSource.getMessage("errors.resource_not_found", new Object[]{"${XMicroServicePascalCase}",id}, Locale.getDefault())
			);
		}
		return ${microService};
	}
	
	@ApiOperation(value = "Delete a specific ${XMicroServicePascalCase}", response = ${XMicroServicePascalCase}.class)
	@CommonApiResponsesQuery
	@DeleteMapping(path = "/${microService}s/{id}")
	public void removeById(@PathVariable(value = "id") Long id) {
		${XMicroServicePascalCase} ${microService} = this.${microService}Service.findById(id);
		if ( ${microService} == null ) {
			throw new ResourceNotFoundException(
				messageSource.getMessage("errors.resource_not_found", new Object[]{"${XMicroServicePascalCase}",id}, Locale.getDefault())
			);
		}
		this.${microService}Service.remove(${microService});
	}
	
	@ApiOperation(value = "Create an ${microService}", response = ${XMicroServicePascalCase}.class)
	@CommonApiResponsesCreation
	@PostMapping(path = "/${microService}s", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public ${XMicroServicePascalCase} create(@RequestBody ${XMicroServicePascalCase} ${microService}) {
		return this.${microService}Service.save(${microService});
	}
	
	@ApiOperation(value = "Update an ${microService}", response = Void.class)
	@CommonApiResponsesUpdate
	@PutMapping(path = "/${microService}s", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void update(@RequestBody ${XMicroServicePascalCase} ${microService}) {
		this.${microService}Service.save(${microService});
	}
	
	@ApiOperation(value = "Clean ${microService}s cache", response = List.class, tags = { "${XMicroServicePascalCase}" } )
	@CommonApiResponsesQuery
	@GetMapping(path = "/${microService}s/cache/clean", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void clean() {
		this.${microService}Service.cleanCache();
	}
	
	

}
