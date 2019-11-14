package ujr.flightstore.airplane.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

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
@NamedQuery(name = "Airplane.findAllByIds", query = "SELECT a from Airplane a WHERE a.id IN :listIds")
@ApiModel(description = "All details about the Airplane. ")
public class Airplane implements Serializable {
	
	
	private static final long serialVersionUID = -3227358413083744913L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String model;
	@ManyToOne(fetch = FetchType.EAGER)
	private Manufacturer manufacturer;
	private Integer seats;
	private Integer rangeKm;

}
