package acambieri.sanbernardo.gestionegare.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="DIVISIONI")
public class Divisione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descrizione;
    private boolean defaultGroup;
    private boolean enabled;
/*    @ManyToMany
    @JoinColumn(name = "ID_DIVISIONE",table = "DIVISIONI_GARA")
    private List<Gara> gare;*/

    public boolean isDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Divisione divisione = (Divisione) o;

        return id == divisione.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
