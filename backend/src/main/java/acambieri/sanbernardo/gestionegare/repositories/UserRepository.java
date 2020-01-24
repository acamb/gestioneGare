package acambieri.sanbernardo.gestionegare.repositories;

import acambieri.sanbernardo.gestionegare.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>{

    User findByUsername(String username);
}
