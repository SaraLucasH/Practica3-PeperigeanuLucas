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
import weka.clusterers.SimpleKMeans;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class App implements CommandLineRunner{

	public static void main(String[] args)  throws Exception{
		SpringApplication.run(App.class, args);
		reglasAsociacion("data/exito.csv","data/reglasExito.txt");
		reglasAsociacion("data/noExito.csv","data/reglasFallos.txt");
		agrupamiento("data/pacientes_fallecidos.csv", "data/protoFallecido.txt");
		agrupamiento("data/pacientes_uci.csv", "data/protoUCI.txt");
		agrupamiento("data/pacientes_resto.csv", "data/protoResto.txt");
	}

	private static void agrupamiento(String path, String rutaSalida) throws Exception{
		//- Número de clusters que vamos a hacer
		int K = 3;
		//- max. num. de iteraciones
		int maxIteration = 100;
		//- fichero CSV:
		// * Este fichero tiene datos numéricos y nominales
		// * Los atributos de cada ejemplo están separados por ";"
		// * La primera fila es el nombre de los atributos
		String inputDataFile = path;

		//- Crear el cargador de CSV
		File inFile = new File(inputDataFile);
		CSVLoader loader = new CSVLoader();
		//- Especificar las caracteristicas del CSV (ver arriba)
		loader.setFieldSeparator(";");
		loader.setNoHeaderRowPresent(false);
		//- Cargar los datos
		loader.setSource(inFile);
		Instances data = loader.getDataSet();

		//- Crear un objeto 'K-Means',
		//  que es el método que vamos a utilizar
		SimpleKMeans kmeans = new SimpleKMeans();
		//- Especificar las características del método
		kmeans.setNumClusters(K);
		kmeans.setMaxIterations(maxIteration);
		kmeans.setPreserveInstancesOrder(true);

		//- Ejecutar el agrupamiento sobre los datos
		try {
			kmeans.buildClusterer(data);
		} catch (Exception ex) {
			System.err.println("Unable to buld Clusterer: " + ex.getMessage());
			ex.printStackTrace();
		}

		//- Mostrar en pantalla los prototipos de cada grupo
		//    (también se les llama 'centroides'
		Instances centroids = kmeans.getClusterCentroids();

		File file= new File(rutaSalida);
		FileWriter fr = null;
		BufferedWriter br = null;
		String dataWithNewLine=data+System.getProperty("line.separator");
		try{
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			//- Mostrar en pantalla los atributos de los datos
			br.write(loader.getStructure()+" ...\n\n");
			br.write("\n");
			for(int i = 0; i < K; i++){
				br.write("Cluster " + i + " tamaño: " + kmeans.getClusterSizes()[i]);
				br.write(" Prototipo: " + centroids.instance(i)+" \n");

			}
			br.write("--- hecho! ---\n");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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


