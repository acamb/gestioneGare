package acambieri.sanbernardo.gestionegare.services;

import acambieri.sanbernardo.gestionegare.exceptions.UserAlreadyExistsException;
import acambieri.sanbernardo.gestionegare.exceptions.WrongPasswordException;
import acambieri.sanbernardo.gestionegare.model.User;
import acambieri.sanbernardo.gestionegare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User saveUser(User user){
        if (user.getId() == null && userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    /**
     *
     * @param user
     * @param oldPassword
     * @param newPassword
     * @param force force the update without checking the old password (admin mode)
     * @return
     */
    @Transactional
    public User updatePassword(User user, String oldPassword,String newPassword,boolean force){
        User saved = userRepository.findByUsername(user.getUsername());
        if(force || passwordEncoder.matches(oldPassword,saved.getPassword())){
            saved.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(saved);
        }
        else{
            throw new WrongPasswordException();
        }
    }

    public List<User> listUsers(){
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }
}
