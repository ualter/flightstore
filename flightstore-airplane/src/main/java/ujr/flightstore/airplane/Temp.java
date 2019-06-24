package ujr.flightstore.airplane;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;

public class Temp {
	
	public static void main(String[] args) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		
		Manufacturer boeing = Manufacturer.builder().id(1L).name("Boeing").build();
		System.out.println("====> Manufacturer");
		System.out.println(mapper.writeValueAsString(boeing));
		
		
		Airplane airplane = Airplane.builder()
				.id(33L)
				.model("B737")
				.rangeKm(15000)
				.seats(180)
				.manufacturer(boeing)
				.build();
		System.out.println("\n====> Airplane");
		System.out.println(mapper.writeValueAsString(airplane));
		
	}

}
