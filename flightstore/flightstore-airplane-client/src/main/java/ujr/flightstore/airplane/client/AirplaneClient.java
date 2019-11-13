package ujr.flightstore.airplane.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ujr.flightstore.airplane.client.view.AirplaneView;


@FeignClient(name = "flightstore-airplane")
public interface AirplaneClient {

	@GetMapping(path = "/flightstore-airplane/api/v1/airplanes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<AirplaneView> list();
	
}
