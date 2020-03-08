package acambieri.sanbernardo.gestionegare.controllers;

import acambieri.sanbernardo.gestionegare.model.*;
import acambieri.sanbernardo.gestionegare.services.GareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gare")
public class GareController {
    
    @Autowired
    private GareService service;

    @Transactional
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @RequestMapping(value="/salva",method= RequestMethod.POST)
    public GaraVO salvaGara(@RequestBody GaraVO gara){
        service.salvaGara(gara);
        return service.getGara(gara);
    }

    @Transactional
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @RequestMapping(value="/update",method= RequestMethod.POST)
    public GaraVO updateGara(@RequestBody GaraVO gara){
        service.salvaGara(gara);
        return service.getGara(gara);
    }
    
    @RequestMapping(value = "/getGare")
    public List<Gara> getGare(@RequestParam("anno") Integer anno)
    {
        return service.getGare(anno);
    }
    
    @RequestMapping(value= "/getGareCompletate")
    public List<Gara> getGareCompletate(@RequestParam("anno") int anno,@RequestParam("tipo") int id){
        return service.getGareCompletate(anno,service.getTipoGara((long)id));
    }
    
    @RequestMapping(value = "/getGara")
    public GaraVO getGara(@RequestParam("id") long id){
        Gara gara = new Gara();
        gara.setId(id);
        return service.getGara(gara);
    }

    @Transactional
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @RequestMapping(value = "/associaListe",method=RequestMethod.PUT)
    public GaraVO associaListe(@RequestBody GaraVO gara){
        return service.salvaGara(gara);
    }

    @Transactional
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
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

    @Transactional
    @Secured({"ROLE_ADMIN"})
    @PostMapping(value = "/divisioni/add")
    public Divisione createDivisione(@RequestBody Divisione divisione){
        return service.saveDivisione(divisione);
    }

    @Transactional
    @Secured({"ROLE_ADMIN"})
    @PostMapping(value = "/divisioni/delete")
    public Boolean deleteDivisione(@RequestBody Divisione divisione){
        service.deleteDivisione(divisione);
        return true;
    }

    @Transactional
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @PostMapping(value = "/divisioni/update")
    public Boolean updateDivisione(@RequestBody Divisione divisione){
        service.saveDivisione(divisione);
        return true;
    }

    @PostMapping(value = "/backup")
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    public GenericResponseWithPayload backup(){
        return new GenericResponseWithPayload<>(service.doBackup());
    }

    @PostMapping(value = "/getTemplateGare")
    public List<TemplateGara> getGareTemplate(){
        return service.getGareTemplate();
    }

    @GetMapping(value = "/getTemplatePunti")
    public List<String> getTemplatePunti(@RequestParam Long id){
        return service.getTemplatePunti(id);
    }
}
