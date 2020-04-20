package es.urjc.etsii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Paciente getPaciente(Long id) {
		return this.pacienteRepository.findById(id.intValue());
	}
}
