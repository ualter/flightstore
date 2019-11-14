package ujr.flightstore.airplane.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
	
	List<Airplane> findByModel(String model);
	List<Airplane> findByManufacturer(Manufacturer manufacturer);
	List<Airplane> findAllByIds(List<Long> listIds);

}
