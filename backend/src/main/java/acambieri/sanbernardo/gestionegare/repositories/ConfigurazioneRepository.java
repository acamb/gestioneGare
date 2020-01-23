package acambieri.sanbernardo.gestionegare.repositories;

import acambieri.sanbernardo.gestionegare.model.Configurazione;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface ConfigurazioneRepository extends CrudRepository<Configurazione,Integer> {

    @Query("select c from Configurazione c")
    Configurazione getConfigurazione();

    @Query(value="SCRIPT TO 'backup.sql'",nativeQuery = true)
    List<String> doBackup();
}
