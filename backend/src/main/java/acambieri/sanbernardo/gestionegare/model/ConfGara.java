package acambieri.sanbernardo.gestionegare.model;

public class ConfGara {
    private long id;
    private Gara gara;
    private Arciere arciere;
    private String gruppo;
    private int punteggio;
    private Divisione divisione;
    private boolean escludiClassifica;

    public boolean isEscludiClassifica() {
        return escludiClassifica;
    }

    public void setEscludiClassifica(boolean escludiClassifica) {
        this.escludiClassifica = escludiClassifica;
    }

    public Divisione getDivisione() {
        return divisione;
    }

    public void setDivisione(Divisione divisione) {
        this.divisione = divisione;
    }

    public Gara getGara() {
        return gara;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGara(Gara gara) {
        this.gara = gara;
    }

    public Arciere getArciere() {
        return arciere;
    }

    public void setArciere(Arciere arciere) {
        this.arciere = arciere;
    }

    public String getGruppo() {
        return gruppo;
    }

    public void setGruppo(String gruppo) {
        this.gruppo = gruppo;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }
}
