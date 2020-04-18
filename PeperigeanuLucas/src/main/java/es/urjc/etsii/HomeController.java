package es.urjc.etsii;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired 
	private FiltradoService filtradoService;
    
    @RequestMapping("/")
    public String inicio()throws Exception {
    	DataModel model = new FileDataModel(new File("data/datasetFiltradoColaborativo.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		LongPrimitiveIterator it=model.getUserIDs();
		Long idU;
		List<RecommendedItem> recommendations;
		while(it.hasNext()) {
			idU=it.next();
			recommendations = recommender.recommend(idU, 3);
			switch(recommendations.size()){
			case 0:
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU),null,null,null);
				break;
			case 1:
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU), recommendations.get(0).getItemID(),
						null,null);
				break;
			case 2:
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU), recommendations.get(0).getItemID(),
		    			recommendations.get(1).getItemID(),null);
				break;
			case 3:
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU), recommendations.get(0).getItemID(),
		    			recommendations.get(1).getItemID(),
		    			recommendations.get(2).getItemID());
				break;
			}	    	 
		}	
    	return "index";
    } 
    
}
