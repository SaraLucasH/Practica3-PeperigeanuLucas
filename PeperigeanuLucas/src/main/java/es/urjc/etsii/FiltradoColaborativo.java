package es.urjc.etsii;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
@Entity
public class FiltradoColaborativo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
    @JoinColumn(name = "paciente_id")	
	private Paciente paciente;
	
	private Long r1;
	private Long r2;
	private Long r3;
	
	public FiltradoColaborativo() {}
	
	public FiltradoColaborativo(Paciente idU,Long r1,Long r2,Long r3) {
		this.paciente=idU;
		this.r1=r1;
		this.r2=r2;
		this.r3=r3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Long getR1() {
		return r1;
	}

	public void setR1(Long r1) {
		this.r1 = r1;
	}

	public Long getR2() {
		return r2;
	}

	public void setR2(Long r2) {
		this.r2 = r2;
	}

	public Long getR3() {
		return r3;
	}

	public void setR3(Long r3) {
		this.r3 = r3;
	}
}
