package acambieri.sanbernardo.gestionegare.controllers;

import acambieri.sanbernardo.gestionegare.mappers.GaraVOMapper;
import acambieri.sanbernardo.gestionegare.model.*;
import acambieri.sanbernardo.gestionegare.services.GareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/gare")
public class GareController {
    
    @Autowired
    private GareService service;


    final GaraVOMapper mapper = new GaraVOMapper();

    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @PostMapping(value="/salva")
    public GaraVO salvaGara(@RequestBody GaraVO gara){
        gara = service.salvaGara(gara);
        return service.getGara(mapper.toEntity(gara));
    }

    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @PostMapping(value="/update")
    public GaraVO updateGara(@RequestBody GaraVO gara){
        gara = service.salvaGara(gara);
        return service.getGara(mapper.toEntity(gara));
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

    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @PutMapping(value = "/associaListe")
    public GaraVO associaListe(@RequestBody GaraVO gara){
        return service.salvaGara(gara);
    }

    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @PutMapping(value = "/salvaClassifica")
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


    @PostMapping(value = "/getClassifiche")
    public List<ClassificaPerDivisione> getClassifiche(@RequestBody GaraVO gara){
        return service.getClassifichePerGara(gara);
    }

    @PostMapping(value = "/getClassificheScontri")
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

    @PostMapping(value = "/backup")
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    public GenericResponseWithPayload<String> backup(){
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
