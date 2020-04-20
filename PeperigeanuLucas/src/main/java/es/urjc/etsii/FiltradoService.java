package es.urjc.etsii;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiltradoService {
	@Autowired
	private FiltradoRepository filtradoRepository;
	
	public void addRecomendacion(Paciente idU,Long r1,Long r2,Long r3) {
		this.filtradoRepository.save(new FiltradoColaborativo(idU,r1,r2,r3));
	}

}
