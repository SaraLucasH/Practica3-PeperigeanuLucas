package es.urjc.etsii;

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
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Component
public class DataBaseLoader {

	String filtradoColaborativo;
	String pathData="data/";
	
	@Autowired
	private FiltradoService filtradoService;
	
	@Autowired
	private PacienteService pacienteService;
	
    @Autowired
    private HechoRepository hechoRepository;
    
    @Autowired
    private HospitalRepository hospitalRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private TiempoRepository tiempoRepository;
    
    @Autowired
    private FiltradoRepository  filtradoRepository;
    
    @PostConstruct
    private void initDatabase() throws Exception{
    	this.hechoRepository.deleteAll();
    	this.hospitalRepository.deleteAll();
    	this.pacienteRepository.deleteAll();
    	this.tiempoRepository.deleteAll();
    	this.filtradoRepository.deleteAll();
    	
    	this.filtradoColaborativo="";
        // Carga de datos
        this.cargaDatos();

    }
    private void cargaDatos() throws Exception {
    	//dimTiempo
    	cargaTiempo(pathData+"dimTIEMPO.csv");
    	
    	//dimHospital a partir de dimLugar
    	cargaHospitales(pathData+"dimLUGAR.csv");
    	
    	//pacientes
    	cargaPacientes(pathData+"P1.csv",pathData+"H1.csv",1,pathData+"datos_filtrado_colaborativo_1.csv");
    	cargaPacientes(pathData+"P2.csv",pathData+"H2.csv",2,pathData+"datos_filtrado_colaborativo_2.csv");
    	cargaPacientes(pathData+"P3.csv",pathData+"H3.csv",3,pathData+"datos_filtrado_colaborativo_3.csv");
    	cargaPacientes(pathData+"P4.csv",pathData+"H4.csv",4,pathData+"datos_filtrado_colaborativo_4.csv");
    	
    	//Filtrado Colaborativo. Juntamos todos los datos en un unico csv
    	cargaFiltradoColaborativoFile(pathData+"datasetFiltradoColaborativo.csv");
    	cargaFiltrado();
    }
    
	private void cargaFiltrado() throws Exception{
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
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU),Long.MIN_VALUE,Long.MIN_VALUE,Long.MIN_VALUE);
				break;
			case 1:
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU), recommendations.get(0).getItemID(),
						Long.MIN_VALUE,Long.MIN_VALUE);
				break;
			case 2:
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU), recommendations.get(0).getItemID(),
		    			recommendations.get(1).getItemID(),Long.MIN_VALUE);
				break;
			case 3:
				this.filtradoService.addRecomendacion(this.pacienteService.getPaciente(idU), recommendations.get(0).getItemID(),
		    			recommendations.get(1).getItemID(),
		    			recommendations.get(2).getItemID());
				break;
			}	    	 
		}
		
	}
	private void cargaFiltradoColaborativoFile(String path) throws IOException {
		File file= new File(path);	
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(file)));
		//"id,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20\n"
		out.write(this.filtradoColaborativo);
		out.close();
		
	}
	
	private void cargaHospitales(String string) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter ficheroEscritura=null;
		PrintWriter pwEscritura = null;

		try {
			archivo = new File(string);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			
			br.readLine();
			
			int i=1;
			while ((linea = br.readLine()) != null) {
				String[]row=linea.split(";");
				hospitalRepository.save(new Hospital(i,row[1],row[2],row[3],row[4]));
				i++;
			}
			br.close();
			fr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Carga hospitales completa");
	}
	
	private void cargaTiempo(String string) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter ficheroEscritura=null;
		PrintWriter pwEscritura = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(string);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			if(archivo.exists()) {
				br.readLine();
				
				while ((linea = br.readLine()) != null) {
					String[]row=linea.split(";");
					
					tiempoRepository.save(new Tiempo(row[1],Integer.parseInt(row[2]),Integer.parseInt(row[3]),Integer.parseInt(row[4]),Integer.parseInt(row[5]),
							row[6],Integer.parseInt(row[7])));
				}
			}
			fr.close();
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}	
		System.out.println("Carga tiempo completa");
	}
	
	private void cargaPacientes(String rutaP,String rutaH,int idH, String pathFiltrado) {
		File archivoPaciente = null;
		FileReader frPaciente = null;
		BufferedReader brPaciente = null;
		
		File archivoHospital = null;
		FileReader frHospital = null;
		BufferedReader brHospital = null;
		
		File archivoFiltrado = null;
		FileReader frFiltrado = null;
		BufferedReader brFiltrado = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivoPaciente = new File(rutaP);
			frPaciente= new FileReader(archivoPaciente);
			brPaciente = new BufferedReader(frPaciente);
			
			archivoHospital = new File(rutaH);
			frHospital= new FileReader(archivoHospital);
			brHospital = new BufferedReader(frHospital);
			
			archivoFiltrado= new File(pathFiltrado);
			frFiltrado= new FileReader(archivoFiltrado);
			brFiltrado = new BufferedReader(frFiltrado);
			
			String lineaPaciente,lineaHospital,lineaFiltrado;
			
			Hospital currentHospital=this.hospitalRepository.findById(idH);
			//System.out.println("Hospital encontrado "+currentHospital.getId()+" "+currentHospital.getNombre());
			brPaciente.readLine();
			brHospital.readLine();
			brFiltrado.readLine();
			
			while ((lineaPaciente = brPaciente.readLine()) != null && (lineaHospital=brHospital.readLine())!=null &&
					(lineaFiltrado=brFiltrado.readLine())!=null) {
				String[]rowP=lineaPaciente.split(";");
				String[]rowH=lineaHospital.split(";");
				
				int i=0;				
				if(rowH[2].length()<10) {
					//Falta el 20 delante del anio
					String[] aux=rowH[2].split("/");
					rowH[2]=aux[0]+"/"+aux[1]+"/"+"20"+aux[2];
				}
				if(rowH[4].contains("S") || rowH[4].contains("s")) {
					rowH[4]="Si";
				}
				
				while(i<rowP.length) {
					if(rowP[i].equalsIgnoreCase("V")|| rowP[i].equalsIgnoreCase("No")) {
						rowP[i]="0";
					}else if(rowP[i].equalsIgnoreCase("M")|| rowP[i].equalsIgnoreCase("Si")) {
						rowP[i]="1";
					}
					i++;
				}
				//id rowH[1]
				Paciente p= new Paciente(Integer.parseInt(rowP[1]),Integer.parseInt(rowP[2]),Integer.parseInt(rowP[3]),Integer.parseInt(rowP[4]),
						Integer.parseInt(rowP[5]),Integer.parseInt(rowP[6]),Integer.parseInt(rowP[7]),
						Integer.parseInt(rowP[8]),Integer.parseInt(rowP[9]),Integer.parseInt(rowP[10]),
						Integer.parseInt(rowP[11]),Integer.parseInt(rowP[12]),Integer.parseInt(rowP[13]));
				
				Hecho h=new Hecho(p,currentHospital,this.tiempoRepository.findByFecha(rowH[2]),Integer.parseInt(rowH[3]),rowH[4],rowH[5],Integer.parseInt(rowH[6]));
				p.setHecho(h);
				
				Paciente pacAux=this.pacienteRepository.save(p);
				
				//Construccion filtrado a partir del id correcto del paciente x del hospital correspondiente
				this.getRateOfItem(pacAux.getId(),lineaFiltrado.substring(lineaFiltrado.indexOf(',')+1));
				
				//System.out.println(this.pacienteRepository.findById(p.getId()));
				//System.out.println(this.tiempoRepository.findByFecha(rowH[2]));
			}	
			brPaciente.close();
			brHospital.close();
			frPaciente.close();
			frHospital.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Carga pacientes y hechos completa");
		
	}
	private void getRateOfItem(int id, String substring) {		
		String[]list=substring.split(",");
		int i=1;
		for(String linea:list) {
			if(!linea.equals("0")) {
				filtradoColaborativo+=id+","+i+","+linea+"\n";
			}
			i++;
		}
	}

}
