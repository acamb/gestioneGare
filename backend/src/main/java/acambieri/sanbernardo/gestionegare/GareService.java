package acambieri.sanbernardo.gestionegare;


import acambieri.sanbernardo.gestionegare.dao.GareDao;
import acambieri.sanbernardo.gestionegare.dao.UpdateDao;
import acambieri.sanbernardo.gestionegare.model.*;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class GareService {
    
    @Autowired
    private GareDao dao;

    @Autowired
    private UpdateDao updateDao;
    
    public GaraVO salvaGara(GaraVO gara){
        dao.saveGara(gara);
        return dao.getGaraVO(gara.getId());
    }

    public GaraVO updateGara(GaraVO gara){
        dao.updateGara(gara);
        return dao.getGaraVO(gara.getId());
    }
    
    public GaraVO getGara(Gara gara){
        return dao.getGaraVO(gara.getId());
    }

    public List<Gara> getGareLight(Integer anno) {
        if (anno != null && anno == 0){
            anno = null;
        }
        return dao.getGare(anno);
    }

    public List<Gara> getGareCompletateLight(int anno,int id) {
        if (anno == 0){
            anno = getAnnoSocietario();
        }
        return dao.getGareCompletate(anno,id);
    }

    public GaraVO associaListe(GaraVO gara) {
        dao.associaGruppiAGara(gara);
        return dao.getGaraVO(gara.getId());
    }

    public GaraVO salvaClassifica(GaraVO gara) {
        dao.inserisciClassifica(gara);
        return dao.getGaraVO(gara.getId());
    }

    public List<Arciere> getArcieri() {
        return dao.getArcieri();
    }

    public int getAnnoSocietario(){
        return dao.getAnnoSocietario();
    }

    public List<TipoGara> getTipiGara() {
        return dao.getTipiGara();
    }

    public List<Divisione> getDivisioni(){
        return dao.getDivisioni();
    }

    public void updateDivisione(Divisione divisione){
        dao.updateDivisione(divisione);
    }

    public void deleteDivisione(Divisione divisione){
        dao.deleteDivisione(divisione);
    }

    public Divisione insertDivisione(Divisione divisione){
        return dao.insertDivisione(divisione);
    }


    public List<ClassificaPerDivisione> getClassifichePerGara(GaraVO gara){
        List<ConfGara> list = dao.getConfGara(gara);
        List<ClassificaPerDivisione> result = parseConfGara(list);
        return result;
    }

    public List<ClassificaPerDivisione> getClassificaIndoorPerDivisioni(int anno) {
        return calcolaClassificaIndoor(anno,false);
    }

    public List<ClassificaPerDivisione> getClassificaIndoorPerGruppi(int anno) {
        return calcolaClassificaIndoor(anno,true);
    }

    public List<ClassificaPerDivisione> getClassificaFiocchiPerDivisioni(int anno) {
        return calcolaClassificaFiocchi(anno,false);
    }

    public List<ClassificaPerDivisione> getClassificaFiocchiPerGruppi(int anno) {
        return calcolaClassificaFiocchi(anno,true);
    }


    private List<ClassificaPerDivisione> calcolaClassificaIndoor(int anno, boolean gruppi){
        TipoGara indoor = dao.getTipiGara().stream().filter(tipo -> tipo.getNome().equals("INDOOR")).findFirst().get();
        List<Gara> gareTorneo = dao.getGareCompletate(anno,indoor.getId());
        return CalcoloPunteggiBL.INSTANCE.calcolaClassificaTorneoSuPunti(gareTorneo,gruppi,dao);
    }

    private List<ClassificaPerDivisione> calcolaClassificaFiocchi(int anno, boolean gruppi){
        TipoGara fiocchi = dao.getTipiGara().stream().filter(tipo -> tipo.getNome().equals("FIOCCHI")).findFirst().get();
        List<Gara> gareTorneo = dao.getGareCompletate(anno,fiocchi.getId());
        return CalcoloPunteggiBL.INSTANCE.calcolaClassificaSuPosizioni(gareTorneo,gruppi,dao);
    }

    private List<ClassificaPerDivisione> parseConfGara(List<ConfGara> list){
        Map<Divisione,List<ArciereVO>> map = new HashMap<>();
        List<ClassificaPerDivisione> result = new ArrayList<>();
        for(ConfGara record : list){
            if(!map.containsKey(record.getDivisione())){
                map.put(record.getDivisione(),new ArrayList<ArciereVO>());
            }
            map.get(record.getDivisione()).add(new ArciereVO(record));
        }
        List<Divisione> keyset = new ArrayList(map.keySet());
        keyset.sort(new Comparator<Divisione>() {
            @Override
            public int compare(Divisione o1, Divisione o2) {
                return o1.getDescrizione().compareTo(o2.getDescrizione());
            }
        });
        for(Divisione d : keyset){
            ClassificaPerDivisione classifica = new ClassificaPerDivisione();
            classifica.setDivisione(d);
            classifica.setArcieri(map.get(d));
            result.add(classifica);
        }
        return result;
    }

    public List<ClassificaPerDivisione> getClassificheScontriPerGruppi(GaraVO gara) {
        return CalcoloPunteggiBL.INSTANCE.calcolaClassificaGaraScontriPerGruppi(gara,dao);
    }

    public String backupDb(){
        File backupFile = updateDao.backupDb();
        StringBuilder b = new StringBuilder();
        try (Stream<String> lines = Files.lines(backupFile.toPath())){
            lines.forEach(line -> b.append(line+"\n"));
        } catch (IOException e) {
            return null;
        }
        return b.toString();
    }
}
