package acambieri.sanbernardo.gestionegare.model;

import org.hibernate.annotations.Cascade;

import java.util.List;

import javax.persistence.*;


@Entity
public class Partecipazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Gara gara;
    @ManyToOne
    private Arciere arciere;
    private String gruppo;
    @Column(name="punteggio_singolo")
    private int punteggio;
    @OneToMany(mappedBy = "partecipazione",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Punteggio> punteggi;
    @ManyToOne
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
