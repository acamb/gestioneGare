package acambieri.sanbernardo.gestionegare.repositories;

import acambieri.sanbernardo.gestionegare.model.Punteggio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PunteggioRepository extends CrudRepository<Punteggio,Integer> {
    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Punteggio SET punteggio = :punteggio WHERE punteggio.id=:id")
    void updatePunteggio(@Param("id") Integer id,@Param("punteggio") int punteggio);
}
