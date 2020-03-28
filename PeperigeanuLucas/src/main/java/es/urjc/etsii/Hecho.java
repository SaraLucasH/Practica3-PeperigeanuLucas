package es.urjc.etsii;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Hecho {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/*@OneToOne
    @JoinColumn(name = "cliente_id", updatable = false, nullable = false)*/
	//private Paciente cliente;
	
	/*@ManyToOne
    @JoinColumn(name = "hospital_id")*/
	//private Hospital hospital;

	/*@ManyToOne
    @JoinColumn(name = "fechaIngreso_id")*/
	//private Tiempo fechaIngreso;
	private int duracion;
	private String uci;
	private String fallecido;
	private int tratamiento;
	
	public Hecho() {}

	public Hecho(Paciente cliente_id, Hospital hospital_id, Tiempo fechaIngreso_id, int duracion, String uci, String fallecido,
			int tratamiento) {
		super();
		/*this.cliente = cliente_id;
		this.hospital = hospital_id;
		this.fechaIngreso= fechaIngreso_id;*/
		this.duracion = duracion;
		this.uci = uci;
		this.fallecido = fallecido;
		this.tratamiento = tratamiento;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	/*public Paciente getCliente_id() {
		return cliente;
	}

	public void setCliente_id(Paciente cliente_id) {
		this.cliente = cliente_id;
	}

	
	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital_id(Hospital hospital_id) {
		this.hospital = hospital_id;
	}

	public Tiempo getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Tiempo fechaIngreso_id) {
		this.fechaIngreso = fechaIngreso_id;
	}*/

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getUci() {
		return uci;
	}

	public void setUci(String uci) {
		this.uci = uci;
	}

	public String getFallecido() {
		return fallecido;
	}

	public void setFallecido(String fallecido) {
		this.fallecido = fallecido;
	}

	public int getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(int tratamiento) {
		this.tratamiento = tratamiento;
	}	
}
