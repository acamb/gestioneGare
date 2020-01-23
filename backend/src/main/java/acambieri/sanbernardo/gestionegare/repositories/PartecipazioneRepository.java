package acambieri.sanbernardo.gestionegare.repositories;

import acambieri.sanbernardo.gestionegare.model.Partecipazione;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartecipazioneRepository extends CrudRepository<Partecipazione,Long> {

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Partecipazione SET punteggio = :punteggio WHERE gara.id=:gara AND arciere.id=:arciere")
    void updatePunteggio(@Param("gara") Long garaId,@Param("arciere") Long arciereId,@Param("punteggio") int punteggio);

    List<Partecipazione> getByGaraId(long idGara);

    void deleteAllByGaraId(long idGara);
}
