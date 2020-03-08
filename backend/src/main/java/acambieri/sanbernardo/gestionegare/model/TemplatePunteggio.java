package acambieri.sanbernardo.gestionegare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "TEMPLATEPUNTEGGIO")
@JsonIgnoreProperties({"templateGara"})
public class TemplatePunteggio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descrizione;
    private int ordine;
    @ManyToOne
    private TemplateGara templateGara;

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

    public int getOrdine() {
        return ordine;
    }

    public void setOrdine(int ordine) {
        this.ordine = ordine;
    }

    public TemplateGara getTemplateGara() {
        return templateGara;
    }

    public void setTemplateGara(TemplateGara templateGara) {
        this.templateGara = templateGara;
    }
}
