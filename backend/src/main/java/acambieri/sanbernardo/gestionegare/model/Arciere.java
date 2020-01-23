package acambieri.sanbernardo.gestionegare.model;


import javax.persistence.*;

@Entity
@Table(name = "ARCIERI")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Arciere {
    protected String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String cognome;
    protected String sesso;

    @ManyToOne
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
