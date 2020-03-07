package acambieri.sanbernardo.gestionegare.services;

import acambieri.sanbernardo.gestionegare.CalcoloPunteggiBL;
import acambieri.sanbernardo.gestionegare.model.*;
import acambieri.sanbernardo.gestionegare.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GareService {
    @Autowired
    private ArciereRepository arciereRepository;
    @Autowired
    private PartecipazioneRepository partecipazioneRepository;
    @Autowired
    private DivisioneRepository divisioneRepository;
    @Autowired
    private GaraRepository garaRepository;
    @Autowired
    private TipoGaraRepository tipoGaraRepository;
    @Autowired
    private ConfigurazioneRepository configurazioneRepository;
    @Autowired
    private TemplateGaraRepository templateGaraRepository;


    public GaraVO salvaGara(GaraVO gara){
        creaPartecipazioni(gara);
        Gara saved = garaRepository.save(gara);
        return new GaraVO(saved,saved.getPartecipazioni());
    }

    private void creaPartecipazioni(GaraVO gara){
        boolean hasPartecipazioni = gara.getId() == null ? false : garaRepository.findById(gara.getId()).get().getPartecipazioni().size() > 0;
        if(hasPartecipazioni){
            partecipazioneRepository.deleteAllByGaraId(gara.getId());

        }
        gara.setPartecipazioni(new ArrayList<>());
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(gara,gara.getGruppoA1(),"A1"));
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(gara,gara.getGruppoA2(),"A2"));
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(gara,gara.getGruppoB1(),"B1"));
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(gara,gara.getGruppoB2(),"B2"));
    }

    private List<Partecipazione> creaPartecipazioniGruppo(Gara gara,List<ArciereVO> arcieri,String gruppo){
        List<Partecipazione> partecipazioni = new ArrayList<>();
        arcieri.forEach(arciere ->
                partecipazioni.add(new Partecipazione()
                        .setArciere(arciere)
                        .setDivisione(arciere.getDivisione())
                        .setGara(gara)
                        .setEscludiClassifica(arciere.isEscludiClassifica())
                        .setGruppo(gruppo)
                        .setPunteggio(arciere.getPunteggio())
                        .setPunteggi(arciere.getPunteggi()
                                            .stream()
                                            .map(p -> new Punteggio().setPunteggio(p)) //TODO[AC] devo settare anche la partecipazione?
                                            .collect(Collectors.toList()))
                )
        );
        return partecipazioni;
    }

    public GaraVO getGara(Gara gara){
        Gara saved = garaRepository.findById(gara.getId()).get();
        /*//Compatibilita': una gara con 1 solo punteggio ha comunque la struttura dei punteggi con il suo punteggio singolo
        saved.getPartecipazioni().stream()
        .filter(p -> p.getPunteggi().size() == 0)
        .forEach(p -> p.getPunteggi().add(p.getPunteggio()));*/
        return new GaraVO(saved,saved.getPartecipazioni());
    }

    public List<Gara> getGare(Integer anno){
        if(anno != null && anno != 0){
            return garaRepository.findAllByAnnoSocietario(anno);
        }
        else{
            List<Gara> result = new ArrayList<>();
            garaRepository.findAll().forEach(result::add);
            return result;
        }
    }

    public List<Gara> getGareCompletate(Integer anno, TipoGara tipoGara){
        if(anno == null){
            anno = getAnnoSocietario();
        }
        return garaRepository.findAllByAnnoSocietarioAndTipiGaraIdAndCompletataIsTrue(anno,tipoGara.getId());
    }

    public GaraVO salvaClassifica(GaraVO gara){
        Stream.of(gara.getGruppoA1().stream(),
                gara.getGruppoA2().stream(),
                gara.getGruppoB1().stream(),
                gara.getGruppoB2().stream()
        ).flatMap(arciere -> arciere)
                .forEach(arciere -> {
                    partecipazioneRepository.updatePunteggio(gara.getId(),arciere.getId(),arciere.getPunteggio());
                });
        garaRepository.setCompletata(gara.getId());
        Gara saved = garaRepository.findById(gara.getId()).get();
        return new GaraVO(saved,saved.getPartecipazioni());
    }

    public List<Arciere> getArcieri(){
        List<Arciere> result = new ArrayList<>();
        arciereRepository.findAll().forEach(result::add);
        return result;
    }

    public List<TipoGara> getTipiGara(){
        List<TipoGara> result = new ArrayList<>();
        tipoGaraRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Divisione> getDivisioni(){
        List<Divisione> result = new ArrayList<>();
        divisioneRepository.findAll().forEach(result::add);
        return result;
    }

    public Divisione saveDivisione(Divisione divisione){
        return divisioneRepository.save(divisione);
    }

    public void deleteDivisione(Divisione divisione){
        divisioneRepository.delete(divisione);
    }

    public List<ClassificaPerDivisione> getClassifichePerGara(GaraVO gara){
        List<Partecipazione> list = partecipazioneRepository.getByGaraId(gara.getId());
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
        TipoGara indoor = tipoGaraRepository.findByNome("INDOOR");
        List<Gara> gareTorneo = garaRepository.findAllByAnnoSocietarioAndTipiGaraIdAndCompletataIsTrue(anno,indoor.getId());
        return CalcoloPunteggiBL.INSTANCE.calcolaClassificaTorneoSuPunti(gareTorneo,gruppi);
    }

    private List<ClassificaPerDivisione> calcolaClassificaFiocchi(int anno, boolean gruppi){
        TipoGara fiocchi = tipoGaraRepository.findByNome("FIOCCHI");
        List<Gara> gareTorneo = garaRepository.findAllByAnnoSocietarioAndTipiGaraIdAndCompletataIsTrue(anno,fiocchi.getId());
        return CalcoloPunteggiBL.INSTANCE.calcolaClassificaSuPosizioni(gareTorneo,gruppi);
    }

    private List<ClassificaPerDivisione> parseConfGara(List<Partecipazione> list){
        Map<Divisione,List<ArciereVO>> map = new HashMap<>();
        List<ClassificaPerDivisione> result = new ArrayList<>();
        for(Partecipazione record : list){
            if(!map.containsKey(record.getDivisione())){
                map.put(record.getDivisione(),new ArrayList<ArciereVO>());
            }
            map.get(record.getDivisione()).add(new ArciereVO(record));
        }
        List<Divisione> keyset = new ArrayList<>(map.keySet());
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
        return CalcoloPunteggiBL.INSTANCE.calcolaClassificaGaraScontriPerGruppi(gara);
    }



    public int getAnnoSocietario(){
        String date = configurazioneRepository.getConfigurazione().getDataAnnoSocietario();
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

    public TipoGara getTipoGara(Long id) {
        return tipoGaraRepository.findById(id).get();
    }

    public String doBackup(){
        configurazioneRepository.doBackup();
        File f = new File("backup.sql");
        StringBuilder b = new StringBuilder();
        try (Stream<String> lines = Files.lines(f.toPath())){
            lines.forEach(line -> b.append(line+"\n"));
        } catch (IOException e) {
            return null;
        }
        return b.toString();
    }

    public List<TemplateGara> getGareTemplate(){
        List<TemplateGara> result = new ArrayList<>();
        templateGaraRepository.findAll().forEach(result::add);
        return result;
    }

    public List<String> getTemplatePunti(int id){
        TemplateGara template = templateGaraRepository.findById(id).get();
        return template.getTemplatePunti()
            .stream()
            .sorted((t,t2) -> t.getOrdine()-t2.getOrdine())
            .map(t -> t.getDescrizione())
            .collect(Collectors.toList());
    }
}
