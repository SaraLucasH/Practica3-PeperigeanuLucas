import java.util.Date;

public class Tiempo {
	private int id;
	private Date fecha;
	private int dia;
	private int mes;
	private int anio;
	private int cuatrim;
	private String diasemana;
	private int esfinde;
	
	
	public Tiempo() {
	}
	public Tiempo(Date fecha, int dia, int mes, int anio, int cuatrim, String diasemana, int esfinde) {
		this.fecha = fecha;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.cuatrim = cuatrim;
		this.diasemana = diasemana;
		this.esfinde = esfinde;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getCuatrim() {
		return cuatrim;
	}
	public void setCuatrim(int cuatrim) {
		this.cuatrim = cuatrim;
	}
	public String getDiasemana() {
		return diasemana;
	}
	public void setDiasemana(String diasemana) {
		this.diasemana = diasemana;
	}
	public int getEsfinde() {
		return esfinde;
	}
	public void setEsfinde(int esfinde) {
		this.esfinde = esfinde;
	}
}
