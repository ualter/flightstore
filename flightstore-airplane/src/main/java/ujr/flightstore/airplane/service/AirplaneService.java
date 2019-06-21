package ujr.flightstore.airplane.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;

@Service
public class AirplaneService {
	
	
	public List<Airplane> list() {
		Manufacturer boeing = Manufacturer.builder().id(1).name("Boeing").build();
		Manufacturer airbus = Manufacturer.builder().id(2).name("Airbus").build();
		
		List<Airplane> list = new ArrayList<Airplane>();
		list.add(Airplane.builder()
				.id(1).model("B737")
				.manufacturer(boeing)
				.build());
		list.add(Airplane.builder()
				.id(1).model("B777")
				.manufacturer(boeing)
				.build());
		list.add(Airplane.builder()
				.id(1).model("A320")
				.manufacturer(airbus)
				.build());
		list.add(Airplane.builder()
				.id(1).model("A350")
				.manufacturer(airbus)
				.build());
		return list;
	}

}
