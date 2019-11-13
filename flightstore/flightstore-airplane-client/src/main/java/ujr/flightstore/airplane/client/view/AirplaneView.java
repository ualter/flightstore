package ujr.flightstore.airplane.client.view;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the Airplane (DTO)")
public class AirplaneView implements Serializable {
	
	private static final long serialVersionUID = 7720908033911892749L;
	
	private Long id;
	private String model;
	private ManufacturerView manufacturer;
	private Integer seats;
	private Integer rangeKm;

}
