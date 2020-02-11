package acambieri.sanbernardo.gestionegare.controllers;

import acambieri.sanbernardo.gestionegare.aspects.HidePassword;
import acambieri.sanbernardo.gestionegare.aspects.RestorePassword;
import acambieri.sanbernardo.gestionegare.controllers.requests.ChangePasswordRequest;
import acambieri.sanbernardo.gestionegare.exceptions.WrongPasswordException;
import acambieri.sanbernardo.gestionegare.model.User;
import acambieri.sanbernardo.gestionegare.repositories.UserRepository;
import acambieri.sanbernardo.gestionegare.services.UserService;
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
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Secured({"ROLE_ADMIN"})
    @HidePassword
    public ResponseEntity<Object> createUser(@RequestBody User user){
        if(user == null || user.getUsername() == null ||
                user.getPassword() == null || user.getRoles() == null ||
                user.getRoles().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User saved = userService.saveUser(user);
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }

    @PutMapping("/update")
    @HidePassword
    @RestorePassword
    public ResponseEntity<Object> updateUser(@RequestBody User user, Principal principal){
        if(principal.getName().equals(user.getUsername()) ||
                ((UsernamePasswordAuthenticationToken)principal).getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(s -> s.equals("ROLE_ADMIN"))){
            if(user.getRoles() == null || user.getRoles().isEmpty()){
                return new ResponseEntity<>("Empty role list not allowed",HttpStatus.BAD_REQUEST);
            }
            User saved=userService.saveUser(user);
            return new ResponseEntity<>(saved,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/password")
    @HidePassword
    public ResponseEntity<Object> updatePassword(@RequestBody ChangePasswordRequest changePasswordRequest,Principal principal){
        if(principal.getName().equals(changePasswordRequest.getUser().getUsername()) ||
                ((UsernamePasswordAuthenticationToken)principal).getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(s -> s.equals("ROLE_ADMIN"))){
            try{
               User inDb= userService.updatePassword(changePasswordRequest.getUser(),
                       changePasswordRequest.getOldPassword(),
                       changePasswordRequest.getNewPassword(),
                       !principal.getName().equals(changePasswordRequest.getUser().getUsername()) //admin mode
               );
                return new ResponseEntity<>(inDb,HttpStatus.OK);
            }
            catch(WrongPasswordException ex){
                return new ResponseEntity<>("Incorrect password",HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/list")
    @HidePassword
    @Secured({"ROLE_ADMIN"})
    public List<User> listUsers(){
        return userService.listUsers();
    }
}
