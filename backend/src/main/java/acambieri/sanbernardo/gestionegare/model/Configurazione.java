package acambieri.sanbernardo.gestionegare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Configurazione {

    @Id
    private Integer id;

    @Column(name = "DATA_ANNO_SOCIETARIO")
    private String dataAnnoSocietario;

    public String getDataAnnoSocietario() {
        return dataAnnoSocietario;
    }

    public void setDataAnnoSocietario(String dataAnnoSocietario) {
        this.dataAnnoSocietario = dataAnnoSocietario;
    }
}
