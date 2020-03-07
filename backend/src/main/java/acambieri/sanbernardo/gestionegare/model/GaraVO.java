package acambieri.sanbernardo.gestionegare.model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class GaraVO extends Gara {
    @Transient
    private List<ArciereVO> gruppoA1;
    @Transient
    private List<ArciereVO> gruppoB1;
    @Transient
    private List<ArciereVO> gruppoA2;
    @Transient
    private List<ArciereVO> gruppoB2;

    public GaraVO(){
        super();
        gruppoA1=new ArrayList<>();
        gruppoB1=new ArrayList<>();
        gruppoA2=new ArrayList<>();
        gruppoB2=new ArrayList<>();
    }

    public GaraVO(Gara gara){
        this();
        this.id = gara.id;
        this.nome = gara.nome;
        this.annoSocietario = gara.annoSocietario;
        this.tipiGara = gara.tipiGara;
        this.divisioni = gara.divisioni;
        this.punteggioMassimo = gara.punteggioMassimo;
        this.completata = gara.completata;
        this.templateGara = gara.templateGara;
    }

    public GaraVO(Gara gara,List<Partecipazione> conf){
        this(gara);
        conf.stream().forEach(configurazione -> {
            ArciereVO arciere = new ArciereVO(configurazione.getArciere());
            arciere.setPunteggio(configurazione.getPunteggio());
            //TODO[AC] risalgono nell'ordine giusto?
            arciere.setPunteggi(configurazione.getPunteggi().stream().map(p -> p.getPunteggio()).collect(Collectors.toList()));
            arciere.setDivisione(configurazione.getDivisione());
            switch(configurazione.getGruppo()){
                case "A":
                case "A1": gruppoA1.add(arciere);
                          break;
                case "A2":gruppoA2.add(arciere);
                            break;
                case "B":
                case "B1": gruppoB1.add(arciere);
                            break;
                case "B2": gruppoB2.add(arciere);
            }
        });
    }

    public List<ArciereVO> getGruppoA1() {
        return gruppoA1;
    }

    public void setGruppoA1(List<ArciereVO> gruppoA1) {
        this.gruppoA1 = gruppoA1;
    }

    public List<ArciereVO> getGruppoB1() {
        return gruppoB1;
    }

    public void setGruppoB1(List<ArciereVO> gruppoB1) {
        this.gruppoB1 = gruppoB1;
    }

    public List<ArciereVO> getGruppoA2() {
        return gruppoA2;
    }

    public void setGruppoA2(List<ArciereVO> gruppoA2) {
        this.gruppoA2 = gruppoA2;
    }

    public List<ArciereVO> getGruppoB2() {
        return gruppoB2;
    }

    public void setGruppoB2(List<ArciereVO> gruppoB2) {
        this.gruppoB2 = gruppoB2;
    }
}
