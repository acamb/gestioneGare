package acambieri.sanbernardo.gestionegare.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class ArciereVO extends Arciere{

    @Transient
    private int punteggio;
    @Transient
    private List<Punteggio> punteggi=new ArrayList<>();
    @Transient
    private BigDecimal sommaPuntiNormalizzata;
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
        this.punteggi = conf.getPunteggi();
    }

    public BigDecimal getSommaPuntiNormalizzata() {
        return sommaPuntiNormalizzata;
    }

    public void setSommaPuntiNormalizzata(BigDecimal sommaPuntiNormalizzata) {
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

    public List<Punteggio> getPunteggi() {
        return punteggi;
    }

    public void setPunteggi(List<Punteggio> punteggi) {
        this.punteggi = punteggi;
    }

    

}
