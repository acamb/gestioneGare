package acambieri.sanbernardo.gestionegare.services;

import acambieri.sanbernardo.gestionegare.CalcoloPunteggiBL;
import acambieri.sanbernardo.gestionegare.mappers.ArciereVOMapper;
import acambieri.sanbernardo.gestionegare.mappers.GaraVOMapper;
import acambieri.sanbernardo.gestionegare.model.*;
import acambieri.sanbernardo.gestionegare.repositories.*;
import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private PunteggioRepository punteggioRepository;
    @Value("${backup.tmp.dir}")
    private String backupTmpDir;

    @Value("${backup.tmp.dir}")
    String backupDir;


    public GaraVO salvaGara(GaraVO gara){
        Gara saved = gara.getId() == null ? null : garaRepository.findById(gara.getId()).orElse(null);
        boolean hasPartecipazioni = saved != null && saved.getId() != null && (saved.getPartecipazioni() != null && !saved.getPartecipazioni().isEmpty());
        if(hasPartecipazioni){
            partecipazioneRepository.deleteAllByGaraId(saved.getId());
            saved.setPartecipazioni(new ArrayList<>());
            gara.setPartecipazioni(new ArrayList<>());
            garaRepository.save(saved);
        }
        GaraVOMapper mapper = new GaraVOMapper();
        Gara garaEntity = mapper.toEntity(gara);
        final Gara savedWithPartecipazioni = garaRepository.save(garaEntity);
        creaPartecipazioni(gara);
        gara.getPartecipazioni()
                .stream()
                .forEach(partecipazione -> {
                    partecipazione.setGara(savedWithPartecipazioni);
                    Partecipazione savedPartecipazione = partecipazioneRepository.save(partecipazione);
                    partecipazione.getPunteggi().forEach(punteggio -> {
                        punteggio.setPartecipazione(savedPartecipazione);
                        punteggioRepository.save(punteggio);
                    });
                });
        savedWithPartecipazioni.setPartecipazioni(gara.getPartecipazioni());
        saved=garaRepository.save(savedWithPartecipazioni);
        GaraVO garaDto = mapper.toDto(saved);
        garaDto.createGruppi(saved.getPartecipazioni());
        return garaDto;
    }

    private void creaPartecipazioni(GaraVO gara){
        Gara garaEntity = new GaraVOMapper().toEntity(gara);
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(garaEntity,gara.getGruppoA1(),"A1"));
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(garaEntity,gara.getGruppoA2(),"A2"));
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(garaEntity,gara.getGruppoB1(),"B1"));
        gara.getPartecipazioni().addAll(creaPartecipazioniGruppo(garaEntity,gara.getGruppoB2(),"B2"));
    }

    private List<Partecipazione> creaPartecipazioniGruppo(Gara gara,List<ArciereVO> arcieri,String gruppo){
        List<Partecipazione> partecipazioni = new ArrayList<>();
        ArciereVOMapper arciereMapper = new ArciereVOMapper();
        arcieri.forEach(arciere -> {
                Arciere arciereEntity = new Arciere();
                arciereMapper.toEntity(arciere,arciereEntity);
                Partecipazione partecipazione = new Partecipazione()
                .setArciere(arciereEntity)
                .setDivisione(arciere.getDivisione())
                .setGara(gara)
                .setEscludiClassifica(arciere.isEscludiClassifica())
                .setGruppo(gruppo)
                .setPunteggio(arciere.getPunteggio());
                partecipazione.setPunteggi(arciere.getPunteggi());
                for(Punteggio p : partecipazione.getPunteggi()){
                    p.setPartecipazione(partecipazione)
                            .setTemplatePunteggio(gara.getTemplateGara().getTemplatePunti().get(
                                    arciere.getPunteggi().indexOf(p)
                            ));
                }
                for(int i = partecipazione.getPunteggi().size();i<gara.getTemplateGara().getPunteggi();i++){
                    partecipazione.getPunteggi().add(new Punteggio()
                            .setPunteggio(0)
                            .setPartecipazione(partecipazione)
                            .setTemplatePunteggio(gara.getTemplateGara().getTemplatePunti().get(i)));
                }
                partecipazioni.add(partecipazione);
            }
        );
        return partecipazioni;
    }

    public GaraVO getGara(Gara gara){
        Gara saved = garaRepository.findById(gara.getId()).orElseThrow();
        GaraVO garaDto = new GaraVO();
        new GaraVOMapper().toDto(saved,garaDto);
        garaDto.createGruppi(saved.getPartecipazioni());
        return garaDto;
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
                    arciere.getPunteggi().forEach(punteggio -> punteggioRepository.updatePunteggio(punteggio.getId(),punteggio.getPunteggio()));
                });
        garaRepository.setCompletata(gara.getId());
        Gara saved = garaRepository.findById(gara.getId()).orElseThrow();
        GaraVO garaDto = new GaraVO();
        new GaraVOMapper().toDto(saved,garaDto);
        garaDto.createGruppi(saved.getPartecipazioni());
        return garaDto;
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
        Sort sort = Sort.by(Sort.Direction.DESC ,"punteggio");
        List<Partecipazione> list = partecipazioneRepository.getByGaraId(gara.getId(),sort);
        return parseConfGara(list);
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
        ArciereVOMapper arciereMapper = new ArciereVOMapper();
        for(Partecipazione partecipazione : list){
            if(!map.containsKey(partecipazione.getDivisione())){
                map.put(partecipazione.getDivisione(),new ArrayList<>());
            }
            ArciereVO arciereVO = new ArciereVO();
            arciereMapper.toDto(partecipazione.getArciere(),arciereVO);
            arciereVO.createPunteggi(partecipazione);
            map.get(partecipazione.getDivisione()).add(arciereVO);
        }
        List<Divisione> keyset = new ArrayList<>(map.keySet());
        keyset.sort((o1, o2) -> o1.getDescrizione().compareTo(o2.getDescrizione()));
        for(Divisione d : keyset){
            ClassificaPerDivisione classifica = new ClassificaPerDivisione();
            classifica.setDivisione(d);
            classifica.setArcieri(map.get(d));
            result.add(classifica);
        }
        return result;
    }

    public List<ClassificaPerDivisione> getClassificheScontriPerGruppi(GaraVO gara) {
        Gara saved = garaRepository.findById(gara.getId()).orElseThrow();
        return CalcoloPunteggiBL.INSTANCE.calcolaClassificaGaraScontriPerGruppi(saved);
    }



    public int getAnnoSocietario(){
        String date = configurazioneRepository.getConfigurazione().getDataAnnoSocietario();
        Calendar c = Calendar.getInstance();
        //parte da 0
        c.set(Calendar.MONTH,Integer.parseInt(date.split("/")[1])-1);
        c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date.split("/")[0]));
        Calendar capodanno = Calendar.getInstance();
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
        return tipoGaraRepository.findById(id).orElseThrow();
    }

    public String doBackup(){
        configurazioneRepository.doBackup(backupDir+"backup.sql");
        File f = new File(backupDir+"backup.sql");
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

    public List<String> getTemplatePunti(Long id){
        Gara gara = garaRepository.findById(id).orElseThrow();
        return gara.getTemplateGara().getTemplatePunti()
            .stream()
            .sorted((t,t2) -> t.getOrdine()-t2.getOrdine())
            .map(TemplatePunteggio::getDescrizione)
            .toList();
    }
}
