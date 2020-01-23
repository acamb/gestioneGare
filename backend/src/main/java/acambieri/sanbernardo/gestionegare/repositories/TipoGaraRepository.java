package acambieri.sanbernardo.gestionegare.repositories;

import acambieri.sanbernardo.gestionegare.model.TipoGara;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoGaraRepository extends CrudRepository<TipoGara,Long>{

    public TipoGara findByNome(String nome);
}
