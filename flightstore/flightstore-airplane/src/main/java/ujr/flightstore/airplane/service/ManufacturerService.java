package ujr.flightstore.airplane.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.repository.ManufacturerRepository;

@Service
public class ManufacturerService {
	
	Logger logger = LoggerFactory.getLogger(ManufacturerService.class);
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	public List<Manufacturer> list() {
		List<Manufacturer> list = new ArrayList<Manufacturer>();
		this.manufacturerRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Page<Manufacturer> list(Pageable pageable) {
		return this.manufacturerRepository.findAll(pageable);
	}
	
	public Manufacturer save(Manufacturer manufacturer) {
		System.out.println(manufacturer.getName());
		return this.manufacturerRepository.save(manufacturer);
	}
	
	//@Cacheable(cacheNames="manufacturers",key="#id")
	public Manufacturer findById(Long id) {
		logger.info("Not from CACHE");
		return this.manufacturerRepository.findById(id).orElse(null);
	}

}
