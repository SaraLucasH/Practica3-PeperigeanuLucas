package es.urjc.etsii;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

//import required classes
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.StringToNominal;

@SpringBootApplication
public class App implements CommandLineRunner{

	public static void main(String[] args)  throws Exception{
		SpringApplication.run(App.class, args);
		reglasAsociacion("data/exito.csv","data/reglasExito.txt");
		reglasAsociacion("data/noExito.csv","data/reglasFallos.txt");
	}
	
	private static void reglasAsociacion(String path,String rutaSalida) throws Exception{
		String inputDataFile = path; 

	 	//- Crear el cargador de CSV
	 	File inFile = new File(inputDataFile);
	 	CSVLoader loader = new CSVLoader();
	   	//- Especificar las caracteristicas del CSV
	    loader.setFieldSeparator(",");
	 	loader.setNoHeaderRowPresent(false);
	 	// get instances object
	 	loader.setSource(inFile);
	    Instances data = loader.getDataSet();
	    //- String to Nominal
	    StringToNominal filter1 = new StringToNominal();
	    filter1.setAttributeRange("first-last");
	    filter1.setInputFormat(data);    
	    data  = Filter.useFilter(data, filter1);
	 	
	 	//- Discretizar en 2 cubos 
	    Discretize  filter2 = new Discretize();
	    filter2.setBins(2);
	    filter2.setInputFormat(data); 
	    data = Filter.useFilter(data, filter2);
	    
	    //- Mostrar en pantalla los atributos de los datos
	    System.out.println(loader.getStructure()+" ...\n\n");

	    // the Apriori alg. Añadimos 10,lift,metrica minima
	    Apriori model = new Apriori();
	    model.setNumRules(10);
	    model.setMetricType(new SelectedTag(1,Apriori.TAGS_SELECTION));
	    model.setMinMetric(1.3);

	    // build model
	    model.buildAssociations(data);
	    
	    File file= new File(rutaSalida);	
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(file)));
	    out.write(model.toString());	
	    out.close();
	}

	/* Redefinición de método para ejecutar acciones sobre la BD. */
    @Override
    @Transactional
    public void run(String... strings) throws Exception {}
}


