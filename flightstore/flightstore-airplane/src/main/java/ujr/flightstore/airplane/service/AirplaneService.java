package ujr.flightstore.airplane.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.repository.AirplaneRepository;

@Service
public class AirplaneService {
	
	@Autowired
	private AirplaneRepository airplaneRepository;
	
	public List<Airplane> list() {
		List<Airplane> list = new ArrayList<Airplane>();
		this.airplaneRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Page<Airplane> list(Pageable pageable) {
		return this.airplaneRepository.findAll(pageable);
	}
	
	public List<Airplane> findByModel(String model) {
		return this.airplaneRepository.findByModel(model);
	}
	
	public List<Airplane> findByManufacturer(Long id) {
		return this.airplaneRepository.findByManufacturer(Manufacturer.builder().id(id).build());
	}
	
	public Airplane save(Airplane airplane) {
		return this.airplaneRepository.save(airplane);
	}
	
	public Airplane findById(Long id) {
		return this.airplaneRepository.findById(id).orElse(null);
	}

}
