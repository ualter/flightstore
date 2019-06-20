package ujr.flightstore.airplane.model;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class Airplane {
	
	private Integer id;
	private String model;
	private Manufacturer manufacturer;
	private Integer seats;
	private Integer rangeKm;

}
