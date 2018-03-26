package acambieri.sanbernardo.gestionegare;

import acambieri.sanbernardo.gestionegare.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gare")
public class GareController {
    
    @Autowired
    private GareService service;
    
    @RequestMapping(value="/salva",method= RequestMethod.POST)
    public GaraVO salvaGara(@RequestBody GaraVO gara){
        service.salvaGara(gara);
        return service.getGara(gara);
    }

    @RequestMapping(value="/update",method= RequestMethod.POST)
    public GaraVO updateGara(@RequestBody GaraVO gara){
        service.updateGara(gara);
        return service.getGara(gara);
    }
    
    @RequestMapping(value = "/getGare")
    public List<Gara> getGare(@RequestParam("anno") Integer anno)
    {
        return service.getGareLight(anno);
    }
    
    @RequestMapping(value= "/getGareCompletate")
    public List<Gara> getGareCompletate(@RequestParam("anno") int anno,@RequestParam("tipo") int id){
        return service.getGareCompletateLight(anno,id);
    }
    
    @RequestMapping(value = "/getGara")
    public GaraVO getGara(@RequestParam("id") long id){
        Gara gara = new Gara();
        gara.setId(id);
        return service.getGara(gara);
    }
    
    @RequestMapping(value = "/associaListe",method=RequestMethod.PUT)
    public GaraVO associaListe(@RequestBody GaraVO gara){
        return service.associaListe(gara);
    }
    
    @RequestMapping(value = "/salvaClassifica",method=RequestMethod.PUT)
    public GaraVO salvaClassifica(@RequestBody GaraVO gara){
        return service.salvaClassifica(gara);
    }
    
    @RequestMapping(value="/getArcieri")
    public List<Arciere> getArcieri(){
        return service.getArcieri();
    }

    @RequestMapping(value="/getTipi")
    public List<TipoGara> getTipiGara(){
        return service.getTipiGara();
    }

    @RequestMapping(value="/getAnnoSocietario")
    public int getAnnoSocietario(){
        return service.getAnnoSocietario();
    }

    @RequestMapping(value = "/getDivisioni")
    public List<Divisione> getDivisioni(){return service.getDivisioni();}

    @RequestMapping(value = "/getClassifiche",method=RequestMethod.POST)
    public List<ClassificaPerDivisione> getClassifiche(@RequestBody GaraVO gara){
        return service.getClassifichePerGara(gara);
    }

    @RequestMapping(value = "/getClassificheScontri",method=RequestMethod.POST)
    public List<ClassificaPerDivisione> getClassificheGara(@RequestBody GaraVO gara){
        return service.getClassificheScontriPerGruppi(gara);
    }

    @GetMapping(value = "/getClassificaIndoorDivisioni")
    public List<ClassificaPerDivisione> getClassificaIndoorPerDivisioni(@RequestParam("anno") int anno){
        return service.getClassificaIndoorPerDivisioni(anno);
    }

    @GetMapping(value = "/getClassificaIndoorGruppi")
    public List<ClassificaPerDivisione> getClassificaIndoorPerGruppi(@RequestParam("anno") int anno){
        return service.getClassificaIndoorPerGruppi(anno);
    }


    @GetMapping(value = "/getClassificaFiocchiDivisioni")
    public List<ClassificaPerDivisione> getClassificaFiocchiPerDivisioni(@RequestParam("anno") int anno){
        return service.getClassificaFiocchiPerDivisioni(anno);
    }

    @GetMapping(value = "/getClassificaFiocchiGruppi")
    public List<ClassificaPerDivisione> getClassificaFiocchiPerGruppi(@RequestParam("anno") int anno){
        return service.getClassificaFiocchiPerGruppi(anno);
    }

    @PostMapping(value = "/divisioni/add")
    public Divisione createDivisione(@RequestBody Divisione divisione){
        return service.insertDivisione(divisione);
    }

    @PostMapping(value = "/divisioni/delete")
    public Boolean deleteDivisione(@RequestBody Divisione divisione){
        service.deleteDivisione(divisione);
        return true;
    }

    @PostMapping(value = "/divisioni/update")
    public Boolean updateDivisione(@RequestBody Divisione divisione){
        service.updateDivisione(divisione);
        return true;
    }

    @PostMapping(value = "/backup")
    public GenericResponseWithPayload backup(){
        //todo[ac] wrapparlo in un oggetto
        return new GenericResponseWithPayload(service.backupDb());
    }
}
