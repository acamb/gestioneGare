package acambieri.sanbernardo.gestionegare.controllers;

import acambieri.sanbernardo.gestionegare.aspects.HidePassword;
import acambieri.sanbernardo.gestionegare.controllers.requests.AuthRequest;
import acambieri.sanbernardo.gestionegare.controllers.responses.AuthResponse;
import acambieri.sanbernardo.gestionegare.model.User;
import acambieri.sanbernardo.gestionegare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import static acambieri.sanbernardo.gestionegare.JwtUtils.generateToken;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${token.validity.seconds}")
    private Long tokenValidity;

    @PostMapping(value = "/auth")
    @HidePassword
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = generateToken(userDetails,tokenValidity,jwtSecret);
        User user = userRepository.findByUsernameAndActiveIsTrue(userDetails.getUsername());
        return ResponseEntity.ok(new AuthResponse(token,user
                ));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


}
