package ujr.flightstore.airplane.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.repository.AirplaneRepository;

@Service
public class AirplaneService {
	
	@Autowired
	private AirplaneRepository airplaneRepository;

	@Cacheable(cacheNames="AIRPLANES",key="'list'",unless="#result.size() == 0")
	public List<Airplane> list() {
		List<Airplane> list = new ArrayList<Airplane>();
		this.airplaneRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Page<Airplane> list(Pageable pageable) {
		return this.airplaneRepository.findAll(pageable);
	}

	@Cacheable(cacheNames="AIRPLANES",key="'listByModel'",unless="#result.size() == 0")
	public List<Airplane> findByModel(String model) {
		return this.airplaneRepository.findByModel(model);
	}
	
	@Cacheable(cacheNames="AIRPLANES",key="'listByManufacturer'",unless="#result.size() == 0")
	public List<Airplane> findByManufacturer(Long id) {
		return this.airplaneRepository.findByManufacturer(Manufacturer.builder().id(id).build());
	}
	
	@CacheEvict(cacheNames="AIRPLANES",key="#airplane.id")
	public void remove(Airplane airplane) {
		this.airplaneRepository.delete(airplane);
	}
	
	public Airplane save(Airplane airplane) {
		return this.airplaneRepository.save(airplane);
	}
	
	@Cacheable(cacheNames="AIRPLANES",key="#id",unless = "#result == null")
	public Airplane findById(Long id) {
		return this.airplaneRepository.findById(id).orElse(null);
	}
	
	
	@CacheEvict(allEntries = true, cacheNames = { "AIRPLANES" })
	public void cleanCache() {
	}

}
