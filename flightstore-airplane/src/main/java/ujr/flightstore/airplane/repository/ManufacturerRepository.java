package ujr.flightstore.airplane.repository;

import org.springframework.data.repository.CrudRepository;

import ujr.flightstore.airplane.model.Manufacturer;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {

}
