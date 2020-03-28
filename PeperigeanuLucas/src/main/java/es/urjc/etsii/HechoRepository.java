package es.urjc.etsii;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HechoRepository extends CrudRepository<Hecho,Long>{

}
