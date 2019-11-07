package ujr.flightstore.airliner.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ujr.flightstore.airliner.model.Airliner;
import ujr.flightstore.airliner.repository.AirlinerRepository;



@Service
public class AirlinerService {
	
	@Autowired
	private AirlinerRepository airlinerRepository;

	@Cacheable(cacheNames="AIRLINER",key="'list'",unless="#result.size() == 0")
	public List<Airliner> list() {
		List<Airliner> list = new ArrayList<Airliner>();
		this.airlinerRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Page<Airliner> list(Pageable pageable) {
		return this.airlinerRepository.findAll(pageable);
	}

	@Cacheable(cacheNames="AIRLINER",key="'listByName'",unless="#result.size() == 0")
	public List<Airliner> findByName(String name) {
		return this.airlinerRepository.findByName(name);
	}
	
	@CacheEvict(cacheNames="AIRLINER",key="#airliner.id")
	public void remove(Airliner airliner) {
		this.airlinerRepository.delete(airliner);
	}
	
	public Airliner save(Airliner airliner) {
		return this.airlinerRepository.save(airliner);
	}
	
	@Cacheable(cacheNames="AIRLINER",key="#id",unless = "#result == null")
	public Airliner findById(Long id) {
		return this.airlinerRepository.findById(id).orElse(null);
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "AIRLINER" })
	public void cleanCache() {
	}

}
