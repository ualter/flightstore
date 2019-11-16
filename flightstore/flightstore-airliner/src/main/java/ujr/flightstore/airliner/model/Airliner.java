package ujr.flightstore.airliner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ujr.flightstore.airplane.client.view.ManufacturerView;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the Airliner.")
public class Airliner implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	@AttributeOverride(name="id", column=@Column(name="airplane_id"))
	private Collection<AirplaneProxy> airplanes = new ArrayList<AirplaneProxy>();
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Embeddable
	public static class AirplaneProxy implements Serializable {

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

}
