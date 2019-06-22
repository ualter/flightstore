package ujr.flightstore.airplane.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;



@Entity
@Data
@Builder
public class Airplane {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String model;
	@ManyToOne
	private Manufacturer manufacturer;
	private Integer seats;
	private Integer rangeKm;

}
