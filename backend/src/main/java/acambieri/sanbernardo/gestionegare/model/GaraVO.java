package acambieri.sanbernardo.gestionegare.model;

import acambieri.sanbernardo.gestionegare.mappers.ArciereVOMapper;
import acambieri.sanbernardo.gestionegare.mappers.Mapped;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class GaraVO{
    @Mapped
    protected Long id;
    @Mapped
    protected String nome;
    @Mapped
    protected int annoSocietario;
    @Mapped
    protected Set<TipoGara> tipiGara;
    @Mapped
    protected Set<Divisione> divisioni;
    @Mapped
    protected boolean completata;
    @Mapped
    protected int punteggioMassimo;

    @Mapped
    protected List<Partecipazione> partecipazioni = new ArrayList<>();
    @Mapped
    protected TemplateGara templateGara;
    private List<ArciereVO> gruppoA1;
    private List<ArciereVO> gruppoB1;
    private List<ArciereVO> gruppoA2;
    private List<ArciereVO> gruppoB2;

    public GaraVO(){
        gruppoA1=new ArrayList<>();
        gruppoB1=new ArrayList<>();
        gruppoA2=new ArrayList<>();
        gruppoB2=new ArrayList<>();
    }

    public void createGruppi(List<Partecipazione> conf){
        ArciereVOMapper arciereMapper = new ArciereVOMapper();
        conf.stream().forEach(configurazione -> {
            ArciereVO arciere = new ArciereVO();
            arciereMapper.toDto(configurazione.getArciere(),arciere);
            arciere.setPunteggio(configurazione.getPunteggio());
            arciere.setPunteggi(configurazione.getPunteggi());
            arciere.setDivisione(configurazione.getDivisione());
            switch(configurazione.getGruppo()){
                case "A","A1":
                    gruppoA1.add(arciere);
                    break;
                case "A2":gruppoA2.add(arciere);
                    break;
                case "B","B1":
                    gruppoB1.add(arciere);
                    break;
                case "B2": gruppoB2.add(arciere);
                    break;
                default:
                    throw new RuntimeException("Gruppo non valido");
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Divisione> getDivisioni() {
        return divisioni;
    }

    public void setDivisioni(Set<Divisione> divisioni) {
        this.divisioni = divisioni;
    }

    public boolean isCompletata() {
        return completata;
    }

    public void setCompletata(boolean completata) {
        this.completata = completata;
    }

    public int getPunteggioMassimo() {
        return punteggioMassimo;
    }

    public void setPunteggioMassimo(int punteggioMassimo) {
        this.punteggioMassimo = punteggioMassimo;
    }

    public List<Partecipazione> getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(List<Partecipazione> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }

    public TemplateGara getTemplateGara() {
        return templateGara;
    }

    public void setTemplateGara(TemplateGara templateGara) {
        this.templateGara = templateGara;
    }
}
