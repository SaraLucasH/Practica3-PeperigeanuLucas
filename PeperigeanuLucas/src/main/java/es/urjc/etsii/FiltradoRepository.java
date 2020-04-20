package es.urjc.etsii;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiltradoRepository extends CrudRepository<FiltradoColaborativo,Long>{
	
}
