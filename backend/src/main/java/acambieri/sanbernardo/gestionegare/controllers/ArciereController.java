package acambieri.sanbernardo.gestionegare.controllers;

import acambieri.sanbernardo.gestionegare.model.Arciere;
import acambieri.sanbernardo.gestionegare.services.ArciereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/arciere")
public class ArciereController {

    @Autowired
    ArciereService service;

    @GetMapping(value="/")
    public List<Arciere> getArcieri(){
        return service.getArcieri();
    }

}