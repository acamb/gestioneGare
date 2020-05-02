package acambieri.sanbernardo.gestionegare.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatisticheTorneo {
    private List<BigDecimal> punteggi;
    private BigDecimal sommaNormalizzata = BigDecimal.ZERO;

    public BigDecimal getSommaNormalizzata() {
        return sommaNormalizzata;
    }

    public void setSommaNormalizzata(BigDecimal sommaNormalizzata) {
        this.sommaNormalizzata = sommaNormalizzata;
    }

    public StatisticheTorneo(){
        punteggi = new ArrayList<>();
    }

    public List<BigDecimal> getPunteggi() {
        return punteggi;
    }

    public void setPunteggi(List<BigDecimal> punteggi) {
        this.punteggi = punteggi;
    }
}

