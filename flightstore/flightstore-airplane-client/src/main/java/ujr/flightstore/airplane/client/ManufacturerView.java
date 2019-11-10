package ujr.flightstore.airplane.client;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManufacturerView implements Serializable {
	
	
	private static final long serialVersionUID = -1666807385785930435L;
	private Long id;
	private String name;

}
