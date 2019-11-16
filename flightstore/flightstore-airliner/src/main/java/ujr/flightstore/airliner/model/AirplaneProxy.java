package ujr.flightstore.airliner.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ujr.flightstore.airplane.client.view.ManufacturerView;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AirplaneProxy implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	@Transient
	private String model;
	@Transient
	private Integer seats;
	@Transient
	private Integer rangeKm;

	@JsonProperty("manufacturer")
	@Transient
	private ManufacturerView manufacturerView;
}