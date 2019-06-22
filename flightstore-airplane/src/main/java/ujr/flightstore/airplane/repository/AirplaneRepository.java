package ujr.flightstore.airplane.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ujr.flightstore.airplane.model.Airplane;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {
	
	List<Airplane> findByModel(String model);

}
