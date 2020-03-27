
public class Hospital {
	private String id;	
	private String nombre;
	private String cpostal;
	private String autopista;
	private String gestor;
	
	public Hospital() {
	}

	public Hospital(String nombre, String cpostal, String autopista, String gestor) {
		this.nombre = nombre;
		this.cpostal = cpostal;
		this.autopista = autopista;
		this.gestor = gestor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCpostal() {
		return cpostal;
	}

	public void setCpostal(String cpostal) {
		this.cpostal = cpostal;
	}

	public String getAutopista() {
		return autopista;
	}

	public void setAutopista(String autopista) {
		this.autopista = autopista;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}
	
}
