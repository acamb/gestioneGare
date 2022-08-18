package acambieri.sanbernardo.gestionegare.model;

import acambieri.sanbernardo.gestionegare.mappers.Mapped;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

public class ArciereVO{

    @Mapped
    private String nome;
    @Mapped
    private Long id;

    @Mapped
    private String cognome;
    @Mapped
    private String sesso;

    @Mapped
    private Divisione divisione;

    private int punteggio;
    private List<Punteggio> punteggi=new ArrayList<>();
    private BigDecimal sommaPuntiNormalizzata;
    private boolean escludiClassifica;

    public ArciereVO(){

    }

    public boolean isEscludiClassifica() {
        return escludiClassifica;
    }

    public void setEscludiClassifica(boolean escludiClassifica) {
        this.escludiClassifica = escludiClassifica;
    }

    public void createPunteggi(Partecipazione conf){
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
        if (o == null || getClass() != o.getClass()) return false;
        ArciereVO arciereVO = (ArciereVO) o;
        return Objects.equals(id, arciereVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Punteggio> getPunteggi() {
        return punteggi;
    }

    public void setPunteggi(List<Punteggio> punteggi) {
        this.punteggi = punteggi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public Divisione getDivisione() {
        return divisione;
    }

    public void setDivisione(Divisione divisione) {
        this.divisione = divisione;
    }


}
