package ${package}.${microService}.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ujr.flightstore.${microService}.model.${XMicroServicePascalCase};
import ujr.flightstore.${microService}.repository.${XMicroServicePascalCase}Repository;


#set( $capital = $microService.toUpperCase() )

@Service
public class ${XMicroServicePascalCase}Service {
	
	@Autowired
	private ${XMicroServicePascalCase}Repository ${microService}Repository;

	@Cacheable(cacheNames="${capital}",key="'list'",unless="#result.size() == 0")
	public List<${XMicroServicePascalCase}> list() {
		List<${XMicroServicePascalCase}> list = new ArrayList<${XMicroServicePascalCase}>();
		this.${microService}Repository.findAll().forEach(list::add);
		return list;
	}
	
	public Page<${XMicroServicePascalCase}> list(Pageable pageable) {
		return this.${microService}Repository.findAll(pageable);
	}

	@Cacheable(cacheNames="${capital}",key="'listByName'",unless="#result.size() == 0")
	public List<${XMicroServicePascalCase}> findByName(String name) {
		return this.${microService}Repository.findByName(name);
	}
	
	@CacheEvict(cacheNames="${capital}",key="#${microService}.id")
	public void remove(${XMicroServicePascalCase} ${microService}) {
		this.${microService}Repository.delete(${microService});
	}
	
	public ${XMicroServicePascalCase} save(${XMicroServicePascalCase} ${microService}) {
		return this.${microService}Repository.save(${microService});
	}
	
	@Cacheable(cacheNames="${capital}",key="#id",unless = "#result == null")
	public ${XMicroServicePascalCase} findById(Long id) {
		return this.${microService}Repository.findById(id).orElse(null);
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "${capital}" })
	public void cleanCache() {
	}

}
