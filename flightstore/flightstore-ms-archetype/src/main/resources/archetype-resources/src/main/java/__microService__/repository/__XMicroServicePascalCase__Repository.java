package ${package}.${microService}.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ujr.flightstore.${microService}.model.${XMicroServicePascalCase};

public interface ${XMicroServicePascalCase}Repository extends JpaRepository<${XMicroServicePascalCase}, Long> {
	
	List<${XMicroServicePascalCase}> findByName(String name);

}
