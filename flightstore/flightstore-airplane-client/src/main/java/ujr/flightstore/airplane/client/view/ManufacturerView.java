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
@ApiModel(description = "All details about the Manufacturer. (DTO)")
public class ManufacturerView implements Serializable {
	
	
	private static final long serialVersionUID = -1666807385785930435L;
	private Long id;
	private String name;

}
