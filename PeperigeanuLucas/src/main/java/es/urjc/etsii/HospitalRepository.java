package es.urjc.etsii;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital,Long>{
	Hospital findById(int id);
}
