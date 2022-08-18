package acambieri.sanbernardo.gestionegare.services;

import acambieri.sanbernardo.gestionegare.model.Divisione;
import acambieri.sanbernardo.gestionegare.repositories.DivisioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DivisioneService {

    @Autowired
    DivisioneRepository repository;


    public void deleteDivisione(Long id){
        repository.deleteById(id);
    }

    public Divisione saveDivisione(Divisione divisione){
        return repository.save(divisione);
    }


    public List<Divisione> getDivisioni(){
        List<Divisione> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

}
