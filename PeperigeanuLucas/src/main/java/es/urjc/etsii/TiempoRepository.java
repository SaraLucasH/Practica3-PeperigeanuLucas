package es.urjc.etsii;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRepository extends CrudRepository<Tiempo,Long>{
	Tiempo findByFecha(String fecha);
}
