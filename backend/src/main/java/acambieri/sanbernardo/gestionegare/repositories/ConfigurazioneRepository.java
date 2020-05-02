package acambieri.sanbernardo.gestionegare.repositories;

import acambieri.sanbernardo.gestionegare.model.Configurazione;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface ConfigurazioneRepository extends CrudRepository<Configurazione,Integer> {

    @Query("select c from Configurazione c")
    Configurazione getConfigurazione();

    @Query(value="SCRIPT TO :file",nativeQuery = true)
    List<String> doBackup(String file);
}
