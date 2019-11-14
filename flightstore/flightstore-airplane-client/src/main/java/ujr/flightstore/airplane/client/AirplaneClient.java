package ujr.flightstore.airplane.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ujr.flightstore.airplane.client.view.AirplaneView;


@FeignClient(name = "flightstore-airplane" )
public interface AirplaneClient {

	@GetMapping(path = "flightstore-airplane/api/v1/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<AirplaneView> list();
	
	@GetMapping(path = "flightstore-airplane/api/v1/airplanes/manufacturer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<AirplaneView> findByManufacturer(@PathVariable(value = "id") Long manufacturerId);
	
	@GetMapping(path = "flightstore-airplane/api/v1/airplanes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public AirplaneView findById(@PathVariable(value = "id") Long id);
	
	@PostMapping(path = "flightstore-airplane/api/v1/airplanes/ids", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<AirplaneView> findByIds(@RequestBody List<Long> ids);
	
}
