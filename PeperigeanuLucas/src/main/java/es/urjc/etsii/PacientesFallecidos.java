package es.urjc.etsii;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
@Entity
public class PacientesFallecidos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private int edad;
    private int sexo;
    private int IMC;
    private int formaFisica ;
    private int tabaquismo ;
    private int alcoholismo ;
    private int colesterol;
    private int hipertension;
    private int cardiopatia ;
    private int reuma ;
    private int EPOC ;
    private int hepatitis ;
    private int cancer;
    private String fallecido;

    public PacientesFallecidos() {
    }

    public PacientesFallecidos(Paciente pacienteId, int edad, int sexo, int iMC, int formaFisica, int tabaquismo, int alcoholismo, int colesterol,
                    int hipertension, int cardiopatia, int reuma, int ePOC, int hepatitis, int cancer) {
        this.paciente = pacienteId;
        this.edad = edad;
        this.sexo = sexo;
        this.IMC = iMC;
        this.formaFisica = formaFisica;
        this.tabaquismo = tabaquismo;
        this.alcoholismo = alcoholismo;
        this.colesterol = colesterol;
        this.hipertension = hipertension;
        this.cardiopatia = cardiopatia;
        this.reuma = reuma;
        this.EPOC = ePOC;
        this.hepatitis = hepatitis;
        this.cancer = cancer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getIMC() {
        return IMC;
    }

    public void setIMC(int iMC) {
        IMC = iMC;
    }

    public int getFormaFisica() {
        return formaFisica;
    }

    public void setFormaFisica(int formaFisica) {
        this.formaFisica = formaFisica;
    }

    public int getTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(int tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public int getAlcoholismo() {
        return alcoholismo;
    }

    public void setAlcoholismo(int alcoholismo) {
        this.alcoholismo = alcoholismo;
    }

    public int getColesterol() {
        return colesterol;
    }

    public void setColesterol(int colesterol) {
        this.colesterol = colesterol;
    }

    public int getHipertension() {
        return hipertension;
    }

    public void setHipertension(int hipertension) {
        this.hipertension = hipertension;
    }

    public int getCardiopatia() {
        return cardiopatia;
    }

    public void setCardiopatia(int cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    public int getReuma() {
        return reuma;
    }

    public void setReuma(int reuma) {
        this.reuma = reuma;
    }

    public int getEPOC() {
        return EPOC;
    }

    public void setEPOC(int ePOC) {
        EPOC = ePOC;
    }

    public int getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(int hepatitis) {
        this.hepatitis = hepatitis;
    }

    public int getCancer() {
        return cancer;
    }

    public void setCancer(int cancer) {
        this.cancer = cancer;
    }

}

