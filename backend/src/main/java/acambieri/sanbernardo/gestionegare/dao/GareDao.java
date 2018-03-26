package acambieri.sanbernardo.gestionegare.dao;

import acambieri.sanbernardo.gestionegare.mapper.GareMapper;
import acambieri.sanbernardo.gestionegare.model.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class GareDao extends CommonDao {

    private GareMapper mapper;

    @Autowired
    public GareDao(@Qualifier("myFactory") SqlSessionFactory factory){
        super(factory);
        mapper = session.getMapper(GareMapper.class);
    }

    public void saveGara(GaraVO gara){
        mapper.saveGara(gara);
        for(Divisione d : gara.getDivisioni()){
            mapper.saveAssociazioneDivisione(d.getId(),gara.getId());
        }
        for(TipoGara t : gara.getTipiGara()){
            mapper.associaTipoGara(gara.getId(),t.getId());
        }
        associaGruppiAGara(gara);
    }

    public void updateGara(GaraVO gara){
        mapper.updateGara(gara);
        mapper.cancellaDivisioniPerGara(gara.getId());
        for(Divisione d : gara.getDivisioni()){
            mapper.saveAssociazioneDivisione(d.getId(),gara.getId());
        }
        mapper.cancellaTipiPerGara(gara.getId());
        for(TipoGara t : gara.getTipiGara()){
            mapper.associaTipoGara(gara.getId(),t.getId());
        }
        associaGruppiAGara(gara);
    }

    public Gara getGara(long id){
        return mapper.getGara(id);
    }

    public GaraVO getGaraVO(long id){
        GaraVO gara = new GaraVO(mapper.getGara(id));
        List<ConfGara> gruppi = mapper.getConfGara(id);
        /*gruppi.forEach(confGara -> {
            ArciereVO arciere = new ArciereVO(confGara.getArciere());
            arciere.setPunteggio(confGara.getPunteggio());
            arciere.setDivisione(confGara.getDivisione());
            arciere.setEscludiClassifica(confGara.isEscludiClassifica());
            if(confGara.getGruppo().equals("A")){
                gara.getGruppoA().add(arciere);
            }
            else{
                gara.getGruppoB().add(arciere);
            }
        });*/
        gara = new GaraVO(gara,gruppi);
        return gara;
    }

    public void associaGruppiAGara(GaraVO gara){
        mapper.cancellaConfGara(gara.getId());
        gara.getGruppoA1().forEach(arciere -> {
            mapper.associaArciereAGara(gara.getId(),arciere.getId(),"A1",arciere.getDivisione() == null ? null : arciere.getDivisione().getId(),arciere.getPunteggio(),"N");
        });
        gara.getGruppoB1().forEach(arciere -> {
            mapper.associaArciereAGara(gara.getId(),arciere.getId(),"B1",arciere.getDivisione() == null ? null : arciere.getDivisione().getId(),arciere.getPunteggio(),arciere.isEscludiClassifica() ? "S" : "N");
        });
        gara.getGruppoA2().forEach(arciere -> {
            mapper.associaArciereAGara(gara.getId(),arciere.getId(),"A2",arciere.getDivisione() == null ? null : arciere.getDivisione().getId(),arciere.getPunteggio(),"N");
        });
        gara.getGruppoB2().forEach(arciere -> {
            mapper.associaArciereAGara(gara.getId(),arciere.getId(),"B2",arciere.getDivisione() == null ? null : arciere.getDivisione().getId(),arciere.getPunteggio(),arciere.isEscludiClassifica() ? "S" : "N");
        });
    }

    public void inserisciClassifica(GaraVO gara){
        /*Stream.concat(gara.getGruppoA1().stream(),gara.getGruppoB1().stream())
                .forEach(arciere -> {
            mapper.updatePunteggio(gara.getId(),arciere.getId(),arciere.getPunteggio());
        });*/
        Stream.of(gara.getGruppoA1().stream(),
                gara.getGruppoA2().stream(),
                gara.getGruppoB1().stream(),
                gara.getGruppoB2().stream()
                ).flatMap(arciere -> arciere)
                .forEach(arciere -> {
                    mapper.updatePunteggio(gara.getId(),arciere.getId(),arciere.getPunteggio());
                });
        mapper.setCompletata(gara.getId());
    }

    public List<Arciere> getArcieri() {
        return mapper.getArcieri();
    }

    public List<Gara> getGare(Integer anno) {
        if(anno != null && anno == 0){
            anno=null;
        }
        return mapper.getGare(anno);
    }

    public List<Gara> getGareCompletate(int anno,long idTipo) {
        return mapper.getGareCompletate(anno,idTipo);
    }

    public List<TipoGara> getTipiGara(){
        return mapper.getTipiGara();
    }

    public int getAnnoSocietario(){
        String date = mapper.getDataAnnoSocietario();
        Calendar c = GregorianCalendar.getInstance();
        //parte da 0
        c.set(Calendar.MONTH,Integer.parseInt(date.split("/")[1])-1);
        c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date.split("/")[0]));
        Calendar capodanno = GregorianCalendar.getInstance();
        //parte da 0
        capodanno.set(Calendar.MONTH,11);
        capodanno.set(Calendar.DAY_OF_MONTH,31);
        // L'anno societario e' la seconda componente di una coppia di anni, es: 2017/2018
        //Se siamo oltre la data impostata dal mese/giorno e prima di capodanno, l'anno e' quello corrente+1, altrimenti quello attuale.
        //Esempio: 01/10/2017, oggi e' il 17/10/17  -> anno = 2018
        //                     oggi e' il 05/01/18  -> anno = 2018
        //                     oggi e' il 10/09/17  -> anno = 2017
        if(new Date().after(c.getTime()) && new Date().before(capodanno.getTime())){
            return c.get(Calendar.YEAR)+1;
        }
        else{
            return c.get(Calendar.YEAR);
        }
    }

    public List<Divisione> getDivisioni(){
        return mapper.getDivisioni();
    }


    public List<ConfGara> getConfGara(Gara gara) {
        return mapper.getConfGara(gara.getId());
    }

    public Divisione insertDivisione(Divisione divisione){
        mapper.saveDivisione(divisione,divisione.isDefaultGroup() ? "S" : "N");
        return divisione;
    }

    public void updateDivisione(Divisione divisione){
        mapper.updateDivisione(divisione,divisione.isDefaultGroup() ? "S" : "N");
    }

    public void deleteDivisione(Divisione divisione){
        mapper.deleteDivisione(divisione);
    }
}
