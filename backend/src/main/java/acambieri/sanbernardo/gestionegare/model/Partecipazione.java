package acambieri.sanbernardo.gestionegare.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.*;


@Entity
public class Partecipazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Gara gara;
    @OneToOne
    private Arciere arciere;
    private String gruppo;
    private int punteggio;
    @OneToMany(cascade = {PERSIST,DETACH,MERGE,REFRESH,REMOVE})
    private List<Punteggio> punteggi;
    @OneToOne
    private Divisione divisione;
    private boolean escludiClassifica;

    public boolean isEscludiClassifica() {
        return escludiClassifica;
    }

    public Partecipazione setEscludiClassifica(boolean escludiClassifica) {
        this.escludiClassifica = escludiClassifica;
        return this;
    }

    public Divisione getDivisione() {
        return divisione;
    }

    public Partecipazione setDivisione(Divisione divisione) {
        this.divisione = divisione;
        return this;
    }

    public Gara getGara() {
        return gara;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partecipazione setGara(Gara gara) {
        this.gara = gara;
        return this;
    }

    public Arciere getArciere() {
        return arciere;
    }

    public Partecipazione setArciere(Arciere arciere) {
        this.arciere = arciere;
        return this;
    }

    public String getGruppo() {
        return gruppo;
    }

    public Partecipazione setGruppo(String gruppo) {
        this.gruppo = gruppo;
        return this;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public Partecipazione setPunteggio(int punteggio) {
        this.punteggio = punteggio;
        return this;
    }

    public List<Punteggio> getPunteggi() {
        return punteggi;
    }

    public Partecipazione setPunteggi(List<Punteggio> punteggi) {
        this.punteggi = punteggi;
        return this;
    }

    
}
