package acambieri.sanbernardo.gestionegare.services;

import acambieri.sanbernardo.gestionegare.model.Arciere;
import acambieri.sanbernardo.gestionegare.repositories.ArciereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArciereService {

    @Autowired
    ArciereRepository arciereRepository;

    public List<Arciere> getArcieri(){
        List<Arciere> result = new ArrayList<>();
        arciereRepository.findAll().forEach(result::add);
        return result;
    }
}
