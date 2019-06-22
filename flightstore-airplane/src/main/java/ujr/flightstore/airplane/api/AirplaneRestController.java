package ujr.flightstore.airplane.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.service.AirplaneService;


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
	@GetMapping("/airplanes")
	public List<Airplane> list() {
		return this.airplaneService.list();
	}

	@GetMapping("/airplanes/manufacturer/{id}")
	public List<Airplane> findByManufacturer(@PathVariable(value = "id") Integer manufacturerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Airplane getAirplane(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
