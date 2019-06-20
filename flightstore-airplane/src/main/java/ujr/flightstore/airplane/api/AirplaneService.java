package ujr.flightstore.airplane.api;

import java.util.List;

import ujr.flightstore.airplane.model.Airplane;

public interface AirplaneService {
	
	public List<Airplane> list();
	public List<Airplane> findByManufacturer(Integer manufacturerId);
	public Airplane getAirplane(Integer id);

}
