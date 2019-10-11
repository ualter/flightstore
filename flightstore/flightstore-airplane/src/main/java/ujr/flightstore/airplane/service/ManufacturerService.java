package ujr.flightstore.airplane.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ujr.flightstore.airplane.model.Manufacturer;
import ujr.flightstore.airplane.repository.ManufacturerRepository;

@Service
public class ManufacturerService {
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Cacheable(cacheNames="MANUFACTURERS",key="'list'",unless="#result.size() == 0")
	public List<Manufacturer> list() {
		List<Manufacturer> list = new ArrayList<Manufacturer>();
		this.manufacturerRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Page<Manufacturer> list(Pageable pageable) {
		return this.manufacturerRepository.findAll(pageable);
	}
	
	public Manufacturer save(Manufacturer manufacturer) {
		return this.manufacturerRepository.save(manufacturer);
	}
	
	@CacheEvict(cacheNames="MANUFACTURERS",key="#manufacturer.id")
	public void remove(Manufacturer manufacturer) {
		this.manufacturerRepository.delete(manufacturer);
	}
	
	@Cacheable(cacheNames="MANUFACTURERS",key="#id",unless = "#result == null")
	public Manufacturer findById(Long id) {
		return this.manufacturerRepository.findById(id).orElse(null);
	}
	
	
	@CacheEvict(allEntries = true, cacheNames = { "MANUFACTURERS" })
	public void cleanCache() {
	}
	
}
