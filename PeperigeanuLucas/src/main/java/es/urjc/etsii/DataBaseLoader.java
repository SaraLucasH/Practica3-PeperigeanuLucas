package es.urjc.etsii;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBaseLoader {

    @Autowired
    private HechoRepository hechoRepository;
    
    @Autowired
    private HospitalRepository hospitalRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private TiempoRepository tiempoRepository;
    
    @PostConstruct
    private void initDatabase() {
    	this.hechoRepository.deleteAll();
    	this.hospitalRepository.deleteAll();
    	this.pacienteRepository.deleteAll();
    	this.tiempoRepository.deleteAll();
    	
        // Carga de datos
        this.cargaDatos();

    }
    private void cargaDatos() {
    	//dimTiempo
    	cargaTiempo("dimTIEMPO.csv");
    	
    	//dimHospital a partir de dimLugar
    	cargaHospitales("dimLUGAR.csv");
    	
    	//pacientes
    	cargaPacientes("P1.csv","H1.csv",1);
    	cargaPacientes("P2.csv","H2.csv",2);
    	cargaPacientes("P3.csv","H3.csv",3);
    	cargaPacientes("P4.csv","H4.csv",4);
    	
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
	
	private void cargaPacientes(String rutaP,String rutaH,int idH) {
		File archivoPaciente = null;
		FileReader frPaciente = null;
		BufferedReader brPaciente = null;
		File archivoHospital = null;
		FileReader frHospital = null;
		BufferedReader brHospital = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivoPaciente = new File(rutaP);
			frPaciente= new FileReader(archivoPaciente);
			brPaciente = new BufferedReader(frPaciente);
			
			archivoHospital = new File(rutaH);
			frHospital= new FileReader(archivoHospital);
			brHospital = new BufferedReader(frHospital);
			
			String lineaPaciente,lineaHospital;
			
			Hospital currentHospital=this.hospitalRepository.findById(idH);
			System.out.println("Hospital encontrado "+currentHospital.getId()+" "+currentHospital.getNombre());
			brPaciente.readLine();
			brHospital.readLine();
			
			while ((lineaPaciente = brPaciente.readLine()) != null && (lineaHospital=brHospital.readLine())!=null) {
				String[]rowP=lineaPaciente.split(";");
				String[]rowH=lineaHospital.split(";");
				
				int i=0;
				
				if(rowH[2].length()<10) {
					//Falta el 20 delante del anio
					String[] aux=rowH[2].split("/");
					rowH[2]=aux[0]+"/"+aux[1]+"/"+"20"+aux[2];
				}
				
				while(i<rowP.length) {
					if(rowP[i].equalsIgnoreCase("V")|| rowP[i].equalsIgnoreCase("No")) {
						rowP[i]="0";
					}else if(rowP[i].equalsIgnoreCase("M")|| rowP[i].equalsIgnoreCase("Si")) {
						rowP[i]="1";
					}
					i++;
				}
				
				Paciente p= new Paciente(Integer.parseInt(rowH[1]),Integer.parseInt(rowP[1]),Integer.parseInt(rowP[2]),Integer.parseInt(rowP[3]),Integer.parseInt(rowP[4]),
						Integer.parseInt(rowP[5]),Integer.parseInt(rowP[6]),Integer.parseInt(rowP[7]),
						Integer.parseInt(rowP[8]),Integer.parseInt(rowP[9]),Integer.parseInt(rowP[10]),
						Integer.parseInt(rowP[11]),Integer.parseInt(rowP[12]),Integer.parseInt(rowP[13]));
				Hecho h=new Hecho(p,currentHospital,this.tiempoRepository.findByFecha(rowH[2]),Integer.parseInt(rowH[3]),rowH[4],rowH[5],Integer.parseInt(rowH[6]));
				p.setHecho(h);
				
				this.pacienteRepository.save(p);
				System.out.println(this.pacienteRepository.findById(p.getId()));
				System.out.println(this.tiempoRepository.findByFecha(rowH[2]));
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

}
