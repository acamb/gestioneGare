package acambieri.sanbernardo.gestionegare.model;

public class Arciere {
    protected String nome;
    protected long id;
    protected String cognome;
    protected String sesso;
    protected Divisione divisione;

    public String getSesso() {
        return sesso;
    }

    public Divisione getDivisione() {
        return divisione;
    }

    public void setDivisione(Divisione divisione) {
        this.divisione = divisione;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Arciere)) return false;

        Arciere arciere = (Arciere) o;

        return id == arciere.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
