package acambieri.sanbernardo.gestionegare.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class ArciereVO extends Arciere{

    @Transient
    private int punteggio;
    @Transient
    private List<Integer> punteggi=new ArrayList<>();
    @Transient
    private Double sommaPuntiNormalizzata;
    @Transient
    private boolean escludiClassifica;

    public ArciereVO(){

    }

    public boolean isEscludiClassifica() {
        return escludiClassifica;
    }

    public void setEscludiClassifica(boolean escludiClassifica) {
        this.escludiClassifica = escludiClassifica;
    }

    public ArciereVO(Arciere arciere){
        this.cognome = arciere.cognome;
        this.nome = arciere.nome;
        this.id = arciere.id;
    }

    public ArciereVO(Partecipazione conf){
        this(conf.getArciere());
        this.punteggio = conf.getPunteggio();
        this.divisione = conf.getDivisione();
    }

    public Double getSommaPuntiNormalizzata() {
        return sommaPuntiNormalizzata;
    }

    public void setSommaPuntiNormalizzata(Double sommaPuntiNormalizzata) {
        this.sommaPuntiNormalizzata = sommaPuntiNormalizzata;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ! (o instanceof Arciere)) return false;

        Arciere arciere = (Arciere) o;

        return id == arciere.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public List<Integer> getPunteggi() {
        return punteggi;
    }

    public void setPunteggi(List<Integer> punteggi) {
        this.punteggi = punteggi;
    }

    

}
