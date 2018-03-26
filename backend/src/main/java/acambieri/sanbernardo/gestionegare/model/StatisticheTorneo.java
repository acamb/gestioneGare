package acambieri.sanbernardo.gestionegare.model;

import java.util.ArrayList;
import java.util.List;

public class StatisticheTorneo {
    private List<Double> punteggi;
    private Double sommaNormalizzata = 0.0;

    public Double getSommaNormalizzata() {
        return sommaNormalizzata;
    }

    public void setSommaNormalizzata(Double sommaNormalizzata) {
        this.sommaNormalizzata = sommaNormalizzata;
    }

    public StatisticheTorneo(){
        punteggi = new ArrayList<>();
    }

    public List<Double> getPunteggi() {
        return punteggi;
    }

    public void setPunteggi(List<Double> punteggi) {
        this.punteggi = punteggi;
    }
}

