package es.urjc.etsii;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        // Carga de datos
        this.cargaDatos();

    }
    private void cargaDatos() {
    	//Primero pacientes
    	cargaPacientesYHospitales("P1.csv","H1.csv");
    	
    }
	private void cargaPacientesYHospitales(String rutaP,String rutaH) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter ficheroEscritura=null;
		PrintWriter pwEscritura = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(rutaP);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			
			while ((linea = br.readLine()) != null) {
				String[]row=linea.split(";");
				Paciente p= new Paciente(Integer.parseInt(row[1]),Integer.parseInt(row[2]),Integer.parseInt(row[3]),Integer.parseInt(row[4]),
						Integer.parseInt(row[5]),Integer.parseInt(row[6]),Integer.parseInt(row[7]),
						Integer.parseInt(row[8]),Integer.parseInt(row[9]),Integer.parseInt(row[10]),
						Integer.parseInt(row[11]),Integer.parseInt(row[12]),Integer.parseInt(row[13]));
				this.pacienteRepository.save(p);	
			}
			/*archivo = new File(rutaP);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
		
			while ((linea = br.readLine()) != null) {
				String[]row=linea.split(";");
				Paciente p= new Paciente(Integer.parseInt(row[1]),Integer.parseInt(row[2]),Integer.parseInt(row[3]),Integer.parseInt(row[4]),
						Integer.parseInt(row[5]),Integer.parseInt(row[6]),Integer.parseInt(row[7]),
						Integer.parseInt(row[8]),Integer.parseInt(row[9]),Integer.parseInt(row[10]),
						Integer.parseInt(row[11]),Integer.parseInt(row[12]),Integer.parseInt(row[13]));
				this.hospitalRepository.save(p);	
			}*/
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
