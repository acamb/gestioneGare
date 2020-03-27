package acambieri.sanbernardo.gestionegare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"partecipazione"})
public class Punteggio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int punteggio;
    @ManyToOne
    private Partecipazione partecipazione;
    @ManyToOne
    private TemplatePunteggio templatePunteggio;

    public Integer getId() {
        return id;
    }

    public Punteggio setId(Integer id) {
        this.id = id;
        return this;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public Punteggio setPunteggio(int punteggio) {
        this.punteggio = punteggio;
        return this;
    }

    public Partecipazione getPartecipazione() {
        return partecipazione;
    }

    public Punteggio setPartecipazione(Partecipazione partecipazione) {
        this.partecipazione = partecipazione;
        return this;
    }

    public TemplatePunteggio getTemplatePunteggio() {
        return templatePunteggio;
    }

    public Punteggio setTemplatePunteggio(TemplatePunteggio templatePunteggio) {
        this.templatePunteggio = templatePunteggio;
        return this;
    }

    @Override
    public String toString() {
        return "Punteggio{" +
                "id=" + id +
                ", punteggio=" + punteggio +
                ", partecipazione=" + partecipazione +
                ", templatePunteggio=" + templatePunteggio +
                '}';
    }
}
