package ujr.flightstore.airplane.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ujr.flightstore.airplane.model.Airplane;


// Check: https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg

@Api(value="Airplane REST API")
@RestController
@RequestMapping(path = "/api/v1")
public class AirplaneController implements AirplaneService {

	@ApiOperation(value = "View a list of available employees", response = List.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/airplanes")
	public List<Airplane> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("/airplanes/manufacturer/{id}")
	public List<Airplane> findByManufacturer(@PathVariable(value = "id") Integer manufacturerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airplane getAirplane(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
