
public class Hecho {

	private int id;
	private int cliente_id;
	private int hospital_id;
	private int fechaIngreso_id;
	private int duracion;
	private String uci;
	private String fallecido;
	private int tratamiento;
	
	public Hecho() {}

	public Hecho(int cliente_id, int hospital_id, int fechaIngreso_id, int duracion, String uci, String fallecido,
			int tratamiento) {
		super();
		this.cliente_id = cliente_id;
		this.hospital_id = hospital_id;
		this.fechaIngreso_id = fechaIngreso_id;
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

	public int getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public int getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}

	public int getFechaIngreso_id() {
		return fechaIngreso_id;
	}

	public void setFechaIngreso_id(int fechaIngreso_id) {
		this.fechaIngreso_id = fechaIngreso_id;
	}

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
