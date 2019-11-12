package ujr.flightstore.airliner.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the Airliner.")
public class Airliner implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "airliner_airplanes", joinColumns = @JoinColumn(name = "airliner_id"))
	@Column(name = "airplane_id")
	private Set<Long> airplanes;
	
	/*
	// Strategy using a Embedded Object as a Proxy
	@Embedded
	@ElementCollection
	private Collection<AirplaneProxy> airplanes = new ArrayList<AirplaneProxy>();
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Embeddable
	public static class AirplaneProxy implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long airplane_id;

	}
	*/
	

}
