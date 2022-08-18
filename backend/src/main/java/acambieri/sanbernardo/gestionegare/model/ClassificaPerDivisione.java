package acambieri.sanbernardo.gestionegare.model;

import java.util.ArrayList;
import java.util.List;

public class ClassificaPerDivisione {
    private Divisione divisione;
    private List<ArciereVO> arcieri;

    public ClassificaPerDivisione(){
        arcieri = new ArrayList<>();
    }

    public Divisione getDivisione() {
        return divisione;
    }

    public void setDivisione(Divisione divisione) {
        this.divisione = divisione;
    }

    public List<ArciereVO> getArcieri() {
        return arcieri;
    }

    public void setArcieri(List<ArciereVO> arcieri) {
        this.arcieri = arcieri;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ClassificaPerDivisione classifica) {
            return divisione.getId().equals(classifica.getDivisione().getId());
        }
        else{
            return false;
        }
    }
}
