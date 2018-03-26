package acambieri.sanbernardo.gestionegare.model;

import java.util.List;
import java.util.Set;

public class Gara {
    protected long id;
    protected String nome;
    protected int annoSocietario;
    protected Set<TipoGara> tipiGara;
    protected List<Divisione> divisioni;
    protected boolean completata;
    protected int punteggioMassimo;

    public int getPunteggioMassimo() {
        return punteggioMassimo;
    }

    public void setPunteggioMassimo(int punteggioMassimo) {
        this.punteggioMassimo = punteggioMassimo;
    }

    public List<Divisione> getDivisioni() {
        return divisioni;
    }

    public void setDivisioni(List<Divisione> divisioni) {
        this.divisioni = divisioni;
    }

    public boolean isCompletata() {
        return completata;
    }

    public void setCompletata(boolean completata) {
        this.completata = completata;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
