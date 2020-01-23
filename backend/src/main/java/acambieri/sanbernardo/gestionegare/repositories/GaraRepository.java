package acambieri.sanbernardo.gestionegare.repositories;

import acambieri.sanbernardo.gestionegare.model.Gara;
import acambieri.sanbernardo.gestionegare.model.TipoGara;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GaraRepository extends CrudRepository<Gara,Long> {

    public List<Gara> findAllByAnnoSocietario(int annoSocietario);

    public List<Gara> findAllByAnnoSocietarioAndTipiGaraIdAndCompletataIsTrue(int annoSocietario, long tipoGaraId);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Gara SET completata=1 WHERE id=:gara")
    void setCompletata(@Param("gara") Long garaId);
}
