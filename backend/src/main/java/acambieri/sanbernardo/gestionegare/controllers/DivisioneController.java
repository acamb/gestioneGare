package acambieri.sanbernardo.gestionegare.controllers;

import acambieri.sanbernardo.gestionegare.model.Divisione;
import acambieri.sanbernardo.gestionegare.services.DivisioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/divisione")
public class DivisioneController {

    @Autowired
    DivisioneService service;

    @GetMapping(value = "/")
    public List<Divisione> getDivisioni(){return service.getDivisioni();}


    @Transactional
    @Secured({"ROLE_ADMIN"})
    @PostMapping(value = "/")
    public Divisione createDivisione(@RequestBody Divisione divisione){
        return service.saveDivisione(divisione);
    }

    @Transactional
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    public Boolean deleteDivisione(@PathVariable("id") Long id){
        service.deleteDivisione(id);
        return true;
    }

    @Transactional
    @Secured({"ROLE_ADMIN","ROLE_EDIT"})
    @PutMapping(value = "/")
    public Boolean updateDivisione(@RequestBody Divisione divisione){
        service.saveDivisione(divisione);
        return true;
    }

}
