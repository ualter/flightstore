package ujr.flightstore.airliner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ujr.flightstore.airliner.model.Airliner;

public interface AirlinerRepository extends JpaRepository<Airliner, Long> {
	
	List<Airliner> findByName(String name);

}
