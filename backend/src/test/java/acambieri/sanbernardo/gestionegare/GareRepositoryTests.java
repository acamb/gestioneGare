package acambieri.sanbernardo.gestionegare;

import acambieri.sanbernardo.gestionegare.model.*;
import acambieri.sanbernardo.gestionegare.repositories.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@DataJpaTest
public class GareRepositoryTests {
  /*  @Autowired
    private ArciereRepository arciereRepository;
    @Autowired
    private GaraRepository garaRepository;
    @Autowired
    private DivisioneRepository divisioneRepository;
    @Autowired
    private PartecipazioneRepository partecipazioneRepository;
    @Autowired
    private TipoGaraRepository tipoGaraRepository;*/
/*

    @Test
    public void creazioneGaraTest(){

        Gara gara = creaGara();
        Gara saved=garaRepository.save(gara);
        Assert.assertTrue(gara.getDivisioni().size() == saved.getDivisioni().size());
        Assert.assertTrue(gara.getTipiGara().size() == saved.getTipiGara().size());
        Assert.assertTrue(gara.getAnnoSocietario() == saved.getAnnoSocietario());
    }

    @Test
    public void creazioneConIscrizioniTest(){
       Gara gara = creaGara();
       List<Arciere> arcieri = new ArrayList<>();
       arciereRepository.findAll().forEach(arcieri::add);
       creaIscrizioni(gara,arcieri);
       Gara saved = garaRepository.save(gara);
       Assert.assertArrayEquals(gara.getPartecipazioni().toArray(),saved.getPartecipazioni().toArray());
    }

    @Test
    public void classificaTest(){
        Gara gara = creaGara();
        List<Arciere> arcieri = new ArrayList<>();
        arciereRepository.findAll().forEach(arcieri::add);
        creaIscrizioni(gara,arcieri);
        Gara saved = garaRepository.save(gara);
        //dal frontend arrivano dei gruppi con il punteggio che non viene automaticamente messo nella partecipazione,simulo con questo meccanismo
        saved.getPartecipazioni().forEach(partecipazione -> partecipazioneRepository.updatePunteggio(partecipazione.getGara().getId(),partecipazione.getArciere().getId(),(int)(Math.random()*99+1)));
        garaRepository.setCompletata(saved.getId());
        //le 2 operazioni eseguite usavano query @Modifying, controllo che sia avvenuto il flush ed il clear del context
        saved = garaRepository.findById(saved.getId()).get();
        saved.getPartecipazioni().forEach(partecipazione -> Assert.assertTrue(partecipazione.getPunteggio() != 0));
        Assert.assertTrue(saved.isCompletata());
        List<Gara> gareCompletate = garaRepository.findAllByAnnoSocietarioAndTipiGaraIdAndCompletataIsTrue(2020,tipoGaraRepository.findByNome("INDOOR").getId());
        Assert.assertTrue(gareCompletate.size() == 1);
    }

    @Test
    public void ricreaPartecipazioniTest(){
        Gara gara = creaGara();
        List<Arciere> arcieri = new ArrayList<>();
        arciereRepository.findAll().forEach(arcieri::add);
        Assert.assertTrue(arcieri.size() > 0);
        creaIscrizioni(gara,arcieri);
        Gara saved = garaRepository.save(gara);
        partecipazioneRepository.deleteAllByGaraId(saved.getId());
        creaIscrizioni(gara,arcieri);
        saved = garaRepository.save(gara);
        saved = garaRepository.findById(saved.getId()).get();
        Assert.assertEquals(saved.getPartecipazioni().size(),arcieri.size());

    }

    public Gara creaGara(){
        Set<Divisione> divisioni = new HashSet<>();
        Set<TipoGara> tipiGara = new HashSet<>();
        divisioneRepository.findAll().forEach(divisioni::add);
        tipiGara.add(tipoGaraRepository.findByNome("INDOOR"));
        Gara gara = new Gara();
        gara.setAnnoSocietario(2020);
        gara.setDivisioni(divisioni);
        gara.setNome("test");
        gara.setPunteggioMassimo(600);
        gara.setTipiGara(tipiGara);
        return gara;
    }

    public void creaIscrizioni(Gara gara,List<Arciere> arcieri){
        gara.setPartecipazioni(new ArrayList<>());
        arcieri.forEach(arciere -> gara.getPartecipazioni().add(new Partecipazione()
            .setArciere(arciere)
                .setGruppo("A1")
                .setDivisione(gara.getDivisioni().iterator().next())
                .setGara(gara)
        ));
    }
*/

}
