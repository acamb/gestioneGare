package acambieri.sanbernardo.gestionegare.controllers;

import acambieri.sanbernardo.gestionegare.aspects.HidePassword;
import acambieri.sanbernardo.gestionegare.aspects.RestorePassword;
import acambieri.sanbernardo.gestionegare.controllers.requests.ChangePasswordRequest;
import acambieri.sanbernardo.gestionegare.model.User;
import acambieri.sanbernardo.gestionegare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    @Secured({"ROLE_ADMIN"})
    @Transactional
    @HidePassword
    public ResponseEntity<Object> createUser(@RequestBody User user){
        if(user == null || user.getUsername() == null ||
                user.getPassword() == null || user.getRoles() == null ||
                user.getRoles().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(userRepository.findByUsername(user.getUsername())!= null){
            return new ResponseEntity<>("User already exists",HttpStatus.BAD_REQUEST);
        }
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }

    @PutMapping("/update")
    @Transactional
    @HidePassword
    @RestorePassword
    public ResponseEntity<Object> updateUser(@RequestBody User user, Principal principal){
        if(principal.getName().equals(user.getUsername()) ||
                ((UsernamePasswordAuthenticationToken)principal).getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(s -> s.equals("ROLE_ADMIN"))){
            if(user.getRoles() == null || user.getRoles().isEmpty()){
                return new ResponseEntity<>("Empty role list not allowed",HttpStatus.BAD_REQUEST);
            }
            User saved=userRepository.save(user);
            return new ResponseEntity<>(saved,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/password")
    @Transactional
    @HidePassword
    public ResponseEntity<Object> updatePassword(@RequestBody ChangePasswordRequest changePasswordRequest,Principal principal){
        if(principal.getName().equals(changePasswordRequest.getUser().getUsername()) ||
                ((UsernamePasswordAuthenticationToken)principal).getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(s -> s.equals("ROLE_ADMIN"))){
            User inDb = userRepository.findByUsername(changePasswordRequest.getUser().getUsername());
            if(inDb.getPassword().equals(changePasswordRequest.getOldPassword())){
                inDb.setPassword(changePasswordRequest.getNewPassword());
                inDb=userRepository.save(inDb);
                return new ResponseEntity<>(inDb,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Incorrect password",HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
