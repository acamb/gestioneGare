package acambieri.sanbernardo.gestionegare.model;

import java.lang.annotation.Target;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TEMPLATEGARA")
public class TemplateGara {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descrizione;
    private int punteggi;
    @OneToMany(mappedBy = "templateGara")
    private List<TemplatePunteggio> templatePunti;

    public int getPunteggi() {
        return punteggi;
    }

    public void setPunteggi(int punteggi) {
        this.punteggi = punteggi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<TemplatePunteggio> getTemplatePunti() {
        return templatePunti;
    }

    public void setTemplatePunti(List<TemplatePunteggio> templatePunti) {
        this.templatePunti = templatePunti;
    }

    
}