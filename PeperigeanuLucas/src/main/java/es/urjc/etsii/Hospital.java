package es.urjc.etsii;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hospital {
	
	@Id
	private int id;	
	private String nombre;
	private String cpostal;
	private String autopista;
	private String gestor;
	// Un hospital para muchos hechos. Campo en clase hecho es "hospital_id".
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private Set<Hecho> hechos;
	
	public Hospital() {
	}

	public Hospital(int id,String nombre, String cpostal, String autopista, String gestor) {
		this.id=id;
		this.nombre = nombre;
		this.cpostal = cpostal;
		this.autopista = autopista;
		this.gestor = gestor;
		this.hechos=new HashSet<Hecho>();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
    public Set<Hecho> getHechos() {
        return hechos;
    }

	public void setHechos(Set<Hecho> hechos) {
		this.hechos = hechos;
	}
	
}
