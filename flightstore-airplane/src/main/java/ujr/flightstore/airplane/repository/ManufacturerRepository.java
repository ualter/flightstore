package ujr.flightstore.airplane.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ujr.flightstore.airplane.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
