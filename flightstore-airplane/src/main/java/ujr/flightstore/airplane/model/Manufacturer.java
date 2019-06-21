package ujr.flightstore.airplane.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Manufacturer {
	
	private Integer id;
	private String name;

}
