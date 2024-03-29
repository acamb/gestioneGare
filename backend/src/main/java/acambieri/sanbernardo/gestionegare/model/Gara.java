package acambieri.sanbernardo.gestionegare.model;

import acambieri.sanbernardo.gestionegare.mappers.Mapped;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name="GARE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"partecipazioni"})
public class Gara {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Mapped
    protected Long id;
    @Mapped
    protected String nome;
    @Mapped
    protected int annoSocietario;
    @ManyToMany(cascade = MERGE)
    @JoinColumn(name = "ID_GARA",table = "GARE_TIPI_GARA")
    @Mapped
    protected Set<TipoGara> tipiGara;
    @ManyToMany
    @JoinColumn(name = "ID_GARA",table = "DIVISIONI_GARA")
    @Mapped
    protected Set<Divisione> divisioni;
    @Mapped
    protected boolean completata;
    @Mapped
    protected int punteggioMassimo;
    @OneToMany(mappedBy = "gara")
    @Mapped
    protected List<Partecipazione> partecipazioni = new ArrayList<>();
    @ManyToOne
    @Mapped
    protected TemplateGara templateGara;

    public int getPunteggioMassimo() {
        return punteggioMassimo;
    }

    public void setPunteggioMassimo(int punteggioMassimo) {
        this.punteggioMassimo = punteggioMassimo;
    }

    public Set<Divisione> getDivisioni() {
        return divisioni;
    }

    public void setDivisioni(Set<Divisione> divisioni) {
        this.divisioni = divisioni;
    }

    public boolean isCompletata() {
        return completata;
    }

    public void setCompletata(boolean completata) {
        this.completata = completata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnnoSocietario() {
        return annoSocietario;
    }

    public void setAnnoSocietario(int annoSocietario) {
        this.annoSocietario = annoSocietario;
    }

    public Set<TipoGara> getTipiGara() {
        return tipiGara;
    }

    public void setTipiGara(Set<TipoGara> tipiGara) {
        this.tipiGara = tipiGara;
    }

    public List<Partecipazione> getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(List<Partecipazione> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }

    public TemplateGara getTemplateGara() {
        return templateGara;
    }

    public void setTemplateGara(TemplateGara templateGara) {
        this.templateGara = templateGara;
    }

    
}
