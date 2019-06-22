package ujr.flightstore.airplane.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ujr.flightstore.airplane.model.Airplane;
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
	
	public List<Airplane> findByModel(String model) {
		return this.airplaneRepository.findByModel(model);
	}
	
	public void save(Airplane airplane) {
		this.airplaneRepository.save(airplane);
	}

}
